package io.github.fuadreza.storelog.repository

import android.util.Log
import io.github.fuadreza.storelog.database.dao.UserDao
import java.lang.Exception

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class UserRepository(private val userDao: UserDao) {

    suspend fun loginUser(username: String, password: String): Boolean {
        try {
            var user = userDao.getUser(username, password)
            Log.d("USERNAMEEEE", "USERNAMENYA : ${user.userName} dan ${user.passWord}")
            if (user.userName == username) {
                return true
            }
        } catch (e: Exception) {
            print(e)
        }
        return false
    }

}