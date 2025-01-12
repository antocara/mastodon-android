package org.joinmastodon.android.data.oauth

import android.accounts.NetworkErrorException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.grishka.appkit.api.Callback
import me.grishka.appkit.api.ErrorResponse
import org.joinmastodon.android.api.requests.accounts.GetOwnAccount
import org.joinmastodon.android.api.requests.oauth.GetOauthToken
import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.model.Account
import org.joinmastodon.android.model.Application
import org.joinmastodon.android.model.Instance
import org.joinmastodon.android.model.Token

class OauthRepository(private val accountSessionManager: AccountSessionManager) : OauthDataSource {

    override suspend fun getOauthToken(code: String): Flow<Boolean> {

        return flow {
            runCatching {
                obtainDataSession()?.let {
                    val token = handleGetToken(dataSession = it, code)
                    emit(token)
                } ?: emit(false)
            }.getOrElse {
                emit(false)
            }
        }
    }

    private suspend fun handleGetToken(
        dataSession: Pair<Instance, Application>,
        code: String
    ): Boolean {
        return suspendCoroutine { continuation ->
            GetOauthToken(
                dataSession.second.clientId,
                dataSession.second.clientSecret,
                code,
                GetOauthToken.GrantType.AUTHORIZATION_CODE
            )
                .setCallback(object : Callback<Token?> {
                    override fun onSuccess(token: Token?) {
                        token?.let {
                            handleGetAccount(dataSession = dataSession, token, continuation)
                        } ?: continuation.resume(false)
                    }

                    override fun onError(error: ErrorResponse) {
                        continuation.resumeWithException(NetworkErrorException())
                    }
                })
                .execNoAuth(dataSession.first.uri)
        }
    }

    private fun handleGetAccount(
        dataSession: Pair<Instance, Application>,
        token: Token,
        continuation: Continuation<Boolean>
    ) {
        GetOwnAccount().setCallback(object : Callback<Account?> {
            override fun onSuccess(account: Account?) {
                AccountSessionManager.getInstance().addAccount(
                    dataSession.first,
                    token,
                    account,
                    dataSession.second, null
                )
                continuation.resume(true)
            }

            override fun onError(error: ErrorResponse) {
                continuation.resume(false)
            }
        })
            .exec(dataSession.first.uri, token)
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