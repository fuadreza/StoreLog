package io.github.fuadreza.storelog.ui.login

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.model.User

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

sealed class LoginState  {
    object LoginSuccess : LoginState()

    data class onLoginState(val user: LiveData<User>): LoginState()

}