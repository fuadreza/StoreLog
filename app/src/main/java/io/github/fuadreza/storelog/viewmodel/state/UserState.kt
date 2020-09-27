package io.github.fuadreza.storelog.viewmodel.state

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.database.entity.User

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

sealed class UserState {
    object LoginSuccess : UserState()
    object LoginError : UserState()
    object RegisterSuccess : UserState()
    object RegisterError : UserState()

    data class OnLoginState(val user: LiveData<User>) : UserState()

}