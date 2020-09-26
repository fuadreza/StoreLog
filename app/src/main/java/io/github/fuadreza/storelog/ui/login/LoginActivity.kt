package io.github.fuadreza.storelog.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.ui.home.SupplyActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java).apply {

        }

        loginViewModel.loginState.observe(this, Observer<LoginState> {state ->
            handleUIState(state)
        })

        btn_login.setOnClickListener {
            val username = ed_username.text.toString()
            val password = ed_password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please input all username or password", Toast.LENGTH_LONG)
                    .show()
            } else {
                CoroutineScope(IO).launch {
                    loginViewModel.login(username, password)
                }
            }
        }
    }

    private fun handleUIState(state: LoginState?) {
        when(state) {
            is LoginState.LoginSuccess -> {
                startActivity(Intent(this, SupplyActivity::class.java))
            }
            is LoginState.LoginError -> {
                Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
            }
        }
    }
}