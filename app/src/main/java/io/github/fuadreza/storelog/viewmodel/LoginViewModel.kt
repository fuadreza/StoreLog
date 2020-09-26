package io.github.fuadreza.storelog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.fuadreza.storelog.database.LocalDatabase
import io.github.fuadreza.storelog.database.entity.User
import io.github.fuadreza.storelog.repository.UserRepository
import io.github.fuadreza.storelog.view.login.LoginState

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginState = MutableLiveData<LoginState>()
    private val repository: UserRepository
    val loginState: LiveData<LoginState> get() = _loginState

    var user: LiveData<User> = MutableLiveData()

    init {
        val userDao = LocalDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    suspend fun login(username: String, password: String) {
        val result = repository.loginUser(username, password)
        if (result) {
            _loginState.postValue(LoginState.LoginSuccess)
        } else {
            _loginState.postValue(LoginState.LoginError)
        }
    }

}