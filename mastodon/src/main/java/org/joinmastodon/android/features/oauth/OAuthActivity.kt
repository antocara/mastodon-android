package org.joinmastodon.android.features.oauth

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.joinmastodon.android.MainActivity
import org.joinmastodon.android.R
import org.joinmastodon.android.ui.utils.UiUtils
import org.koin.android.ext.android.inject

class OAuthActivity : AppCompatActivity() {

    companion object {
        const val ERROR = "error"
        const val CODE = "code"
        const val ERROR_DESCRIPTION = "error_description"
    }

    private val oauthViewModel: OAuthViewModel by inject()

    private val progress by lazy {
        buildProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        UiUtils.setUserPreferredTheme(this)
        super.onCreate(savedInstanceState)
        observeLoginState()
        initLogin()
    }

    private fun initLogin() {
        runCatching { oauthViewModel.validateQueryParamsAndGetCode(intent, isTaskRoot) }.fold(
            {
                progress.show()
                oauthViewModel.handleLogin(it)
            },
            {
                handleError()
                return
            }
        )
    }

    private fun observeLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                oauthViewModel.loginState.collect { state ->
                    when (state) {
                        is LoginState.Success -> {
                            progress.dismiss()
                            finish()
                            openMainActivity()
                        }
                        is LoginState.Failure -> {
                            handleError()
                        }
                        is LoginState.Loading -> progress.show()
                    }
                }
            }
        }
    }

    private fun buildProgress(): ProgressDialog {
        return ProgressDialog(this@OAuthActivity).apply {
            setMessage(getString(R.string.finishing_auth))
            setCancelable(false)
        }
    }

    //TODO create errors flows
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

    private fun handleError() {
        progress.dismiss()
        Toast.makeText(this, "Errorrrrrrrr", Toast.LENGTH_LONG).show()
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