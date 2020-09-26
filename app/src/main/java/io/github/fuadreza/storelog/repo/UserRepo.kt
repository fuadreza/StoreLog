package io.github.fuadreza.storelog.repo

import android.util.Log
import io.github.fuadreza.storelog.localdb.user.UserDao
import java.lang.Exception

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class UserRepo(private val userDao: UserDao) {

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