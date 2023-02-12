package org.joinmastodon.android.features.oauth

import android.accounts.NetworkErrorException
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.joinmastodon.android.data.oauth.OauthDataSource

class OAuthViewModel(private val oauthDataSource: OauthDataSource) : ViewModel() {

    companion object {
        const val IS_TASK_ROOT_KEY = "isTaskRoot"
    }

    private val _loginState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState.Loading)

    val loginState: MutableStateFlow<LoginState> = _loginState

    fun validateQueryParamsAndGetCode(intent: Intent, isTaskRoot: Boolean): String {

        val uri = intent.data ?: throw NetworkErrorException()

        if (isRoot(intent, isTaskRoot)) {
            throw NetworkErrorException()
        }

        uri.getQueryParameter(OAuthActivity.ERROR)?.let {
            throw NetworkErrorException()
        }

        return obtainCode(uri)
    }

    fun handleLogin(code: String) {
        viewModelScope.launch {
            oauthDataSource.getOauthToken(code).collect {
                when (it) {
                    true -> _loginState.value = LoginState.Success
                    false -> _loginState.value = LoginState.Failure
                }
            }
        }
    }

    private fun obtainCode(uri: Uri): String {
        val code = uri.getQueryParameter(OAuthActivity.CODE)
        return code ?: throw NetworkErrorException()
    }

    private fun isRoot(intent: Intent, isTaskRoot: Boolean): Boolean {
        return if (intent.extras?.getBoolean(IS_TASK_ROOT_KEY)?.not() == true) {
            isTaskRoot
        } else {
            false
        }
    }
}

sealed class LoginState {
    object Success : LoginState()
    object Loading : LoginState()
    object Failure : LoginState()
}