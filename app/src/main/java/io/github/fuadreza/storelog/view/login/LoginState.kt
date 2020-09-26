package io.github.fuadreza.storelog.view.login

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.database.entity.User

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

sealed class LoginState  {
    object LoginSuccess : LoginState()
    object LoginError: LoginState()

    data class onLoginState(val user: LiveData<User>): LoginState()

}