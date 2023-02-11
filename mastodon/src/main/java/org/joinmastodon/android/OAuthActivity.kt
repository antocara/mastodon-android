package org.joinmastodon.android

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import me.grishka.appkit.api.Callback
import me.grishka.appkit.api.ErrorResponse
import org.joinmastodon.android.api.requests.accounts.GetOwnAccount
import org.joinmastodon.android.api.requests.oauth.GetOauthToken
import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.model.Account
import org.joinmastodon.android.model.Application
import org.joinmastodon.android.model.Instance
import org.joinmastodon.android.model.Token
import org.joinmastodon.android.ui.utils.UiUtils

class OAuthActivity : Activity() {

    companion object {
        const val ERROR = "error"
        const val CODE = "code"
        const val ERROR_DESCRIPTION = "error_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        UiUtils.setUserPreferredTheme(this)
        super.onCreate(savedInstanceState)

        val code = validateQueryParamsAndGetCode() ?: return
        val dataSession = obtainDataSession() ?: return

        val progress = buildProgress()
        progress.show()

        handleGetOauthToken(dataSession = dataSession, code = code, progress = progress)
    }

    private fun validateQueryParamsAndGetCode(): String? {

        val uri = intent.data
        if (uri == null || isTaskRoot) {
            finish()
            return null
        }
        uri.getQueryParameter(ERROR)?.let {
            handleErrorQueryParams(uri)
        }

        val code = uri.getQueryParameter(CODE)
        return if (code == null) {
            finish()
            null
        } else {
            code
        }
    }

    private fun obtainDataSession(): Pair<Instance, Application>? {
        val instance = AccountSessionManager.getInstance().authenticatingInstance
        val app = AccountSessionManager.getInstance().authenticatingApp
        if (instance == null || app == null) {
            finish()
        }
        return Pair(instance, app)
    }

    private fun handleGetOauthToken(
        dataSession: Pair<Instance, Application>,
        code: String,
        progress: ProgressDialog
    ) {
        GetOauthToken(
            dataSession.second.clientId,
            dataSession.second.clientSecret,
            code,
            GetOauthToken.GrantType.AUTHORIZATION_CODE
        )
            .setCallback(object : Callback<Token?> {
                override fun onSuccess(token: Token?) {
                    GetOwnAccount()
                        .setCallback(object : Callback<Account?> {
                            override fun onSuccess(account: Account?) {
                                AccountSessionManager.getInstance()
                                    .addAccount(
                                        dataSession.first,
                                        token,
                                        account,
                                        dataSession.second,
                                        null
                                    )
                                progress.dismiss()
                                finish()
                                openMainActivity()
                            }

                            override fun onError(error: ErrorResponse) {
                                handleError(error)
                                progress.dismiss()
                            }
                        })
                        .exec(dataSession.first.uri, token)
                }

                override fun onError(error: ErrorResponse) {
                    handleError(error)
                    progress.dismiss()
                }
            })
            .execNoAuth(dataSession.first.uri)
    }

    private fun buildProgress(): ProgressDialog {
        return ProgressDialog(this@OAuthActivity).apply {
            setMessage(getString(R.string.finishing_auth))
            setCancelable(false)
        }
    }

    private fun handleErrorQueryParams(uri: Uri) {
        var error = uri.getQueryParameter(ERROR_DESCRIPTION)
        if (TextUtils.isEmpty(error)) {
            error = uri.getQueryParameter(ERROR)
        }
        showToast(error.orEmpty())
        finish()
        restartMainActivity()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun handleError(error: ErrorResponse) {
        error.showToast(this@OAuthActivity)
        finish()
        restartMainActivity()
    }

    private fun restartMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    private fun openMainActivity() {
        val intent = Intent(this@OAuthActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}