package io.github.fuadreza.storelog.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.fuadreza.storelog.localdb.LocalDatabase
import io.github.fuadreza.storelog.model.User
import io.github.fuadreza.storelog.repo.UserRepo

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginState = MutableLiveData<LoginState>()
    private val repository: UserRepo
    val loginState: LiveData<LoginState> get() = _loginState

    var user: LiveData<User> = MutableLiveData()

    init {
        val userDao = LocalDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UserRepo(userDao)
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