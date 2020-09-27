package io.github.fuadreza.storelog.view.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.viewmodel.LoginViewModel
import io.github.fuadreza.storelog.viewmodel.state.UserState
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 27/09/2020.
 *
 */

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        lifecycle.addObserver(loginViewModel)

        clickListener()

    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun observe() {
        loginViewModel.userState.observe(this, Observer { state ->
            handleUIState(state)
        })
    }

    private fun handleUIState(state: UserState?) {
        when (state) {
            is UserState.RegisterSuccess -> {
                toast("Success Registering User")
                finish()
            }
            is UserState.RegisterError -> toast("Error Registering User")
        }
    }

    private fun clickListener() {
        btn_register.setOnClickListener {
            val username = ed_username.text.toString()
            val password = ed_password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please input all username or password", Toast.LENGTH_LONG)
                    .show()
            } else {
                CoroutineScope(IO).launch {
                    loginViewModel.register(username, password)
                }
            }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG)
            .show()
    }
}