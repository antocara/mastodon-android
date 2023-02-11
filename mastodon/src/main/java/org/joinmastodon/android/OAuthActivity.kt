package org.joinmastodon.android

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.joinmastodon.android.data.oauth.OauthDataSource
import org.joinmastodon.android.ui.utils.UiUtils
import org.koin.android.ext.android.inject

class OAuthActivity : AppCompatActivity() {

    companion object {
        const val ERROR = "error"
        const val CODE = "code"
        const val ERROR_DESCRIPTION = "error_description"
    }

    private val oauthDataSource: OauthDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        UiUtils.setUserPreferredTheme(this)
        super.onCreate(savedInstanceState)

        val code = validateQueryParamsAndGetCode() ?: return

        val progress = buildProgress()
        progress.show()

        handleGetOauthToken(code = code, progress = progress)
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

    private fun handleGetOauthToken(
        code: String,
        progress: ProgressDialog
    ) {
        lifecycleScope.launch {
            if (oauthDataSource.getOauthToken(code)) {
                progress.dismiss()
                finish()
                openMainActivity()
            } else {
                handleError()
                progress.dismiss()
            }
        }
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

    private fun handleError() {
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