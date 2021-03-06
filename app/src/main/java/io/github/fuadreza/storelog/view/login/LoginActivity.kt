package io.github.fuadreza.storelog.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.view.home.SupplyActivity
import io.github.fuadreza.storelog.view.register.RegisterActivity
import io.github.fuadreza.storelog.viewmodel.LoginViewModel
import io.github.fuadreza.storelog.viewmodel.state.UserState
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        lifecycle.addObserver(loginViewModel)

        clickListener()

    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun observe() {
        loginViewModel.userState.observe(this, Observer<UserState> { state ->
            handleUIState(state)
        })
    }

    private fun clickListener() {
        btn_login.setOnClickListener {
            val username = ed_username.text.toString()
            val password = ed_password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                toast("Please input all username or password")
            } else {
                CoroutineScope(IO).launch {
                    loginViewModel.login(username, password)
                }
            }
        }

        btn_to_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun handleUIState(state: UserState?) {
        when (state) {
            is UserState.LoginSuccess -> {
                startActivity(Intent(this, SupplyActivity::class.java))
            }
            is UserState.LoginError -> {
                toast("User not found")
            }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}