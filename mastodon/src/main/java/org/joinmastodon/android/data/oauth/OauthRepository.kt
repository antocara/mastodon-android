package org.joinmastodon.android.data.oauth

import android.accounts.NetworkErrorException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.grishka.appkit.api.Callback
import me.grishka.appkit.api.ErrorResponse
import org.joinmastodon.android.api.MastodonAPIRequest
import org.joinmastodon.android.api.requests.accounts.GetOwnAccount
import org.joinmastodon.android.api.requests.oauth.GetOauthToken
import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.extensions.asResult
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest
import org.joinmastodon.android.data.mastodonapi.request.OauthTokenBody
import org.joinmastodon.android.data.mastodonapi.request.OauthTokenRequest
import org.joinmastodon.android.data.mastodonapi.request.OwnAccountRequest
import org.joinmastodon.android.data.mastodonapi.response.OauthTokenResponse
import org.joinmastodon.android.data.mastodonapi.response.OwnAccountResponse
import org.joinmastodon.android.data.mastodonapi.response.toOld
import org.joinmastodon.android.data.mastodonapi.response.toOldToken
import org.joinmastodon.android.data.oauth.model.GrantType
import org.joinmastodon.android.model.Account
import org.joinmastodon.android.model.Application
import org.joinmastodon.android.model.Instance
import org.joinmastodon.android.model.Token
import retrofit2.Response

class OauthRepository(
    private val accountSessionManager: AccountSessionManager,
    private val oauthTokenRequest: OauthTokenRequest,
    private val ownAccountRequest: OwnAccountRequest,
) : OauthDataSource {

    override suspend fun getOauthToken(code: String): Flow<Boolean> {

        return flow {
            obtainDataSession()?.let {
                val token = handleGetToken(dataSession = it, code)
                emit(token)
            } ?: emit(false)

        }.flowOn(Dispatchers.IO)
    }

    private suspend fun handleGetToken(
        dataSession: Pair<Instance, Application>,
        code: String
    ): Boolean {

        return getOauthToken(dataSession, code)?.let {
            getAccount(dataSession, it.toOldToken())
        } ?: false
    }

    private suspend fun getOauthToken(
        dataSession: Pair<Instance, Application>,
        code: String
    ): OauthTokenResponse? {

        return runCatching {
            oauthTokenRequest.getToken(dataSession.second, dataSession.first, code).execute()
        }.getOrNull()
    }

    private suspend fun getAccount(
        dataSession: Pair<Instance, Application>,
        token: Token
    ): Boolean {

        val account = runCatching {
            ownAccountRequest.getAccount(token = token, instance = dataSession.first).execute()
        }.getOrNull().also {
            it?.run {
                addAccountData(dataSession, token, this.toOld())
            }
        }

        return account != null
    }

    private fun addAccountData(
        dataSession: Pair<Instance, Application>,
        token: Token,
        account: Account
    ) {
        AccountSessionManager.getInstance().addAccount(
            dataSession.first,
            token,
            account,
            dataSession.second, null
        )
    }

    private fun obtainDataSession(): Pair<Instance, Application>? {
        val instance = accountSessionManager.authenticatingInstance
        val app = accountSessionManager.authenticatingApp
        if (instance == null || app == null) {
            return null
        }
        return Pair(instance, app)
    }
}