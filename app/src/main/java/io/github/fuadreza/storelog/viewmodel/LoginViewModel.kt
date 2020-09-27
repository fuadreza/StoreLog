package io.github.fuadreza.storelog.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.fuadreza.storelog.database.entity.User
import io.github.fuadreza.storelog.repository.UserRepository
import io.github.fuadreza.storelog.viewmodel.state.UserState

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class LoginViewModel @ViewModelInject constructor(private val repository: UserRepository) :
    ViewModel(), LifecycleObserver {

    private val _loginState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _loginState
    var user: LiveData<User> = MutableLiveData()

    fun login(username: String, password: String) {

        val result = repository.loginUser(username, password)
        if (result) {
            _loginState.postValue(UserState.LoginSuccess)
        } else {
            _loginState.postValue(UserState.LoginError)
        }
    }

    fun register(username: String, password: String) {
        val result = repository.registerUser(username, password)
        if (result) {
            _loginState.postValue(UserState.RegisterSuccess)
        } else {
            _loginState.postValue(UserState.RegisterError)
        }
    }

}