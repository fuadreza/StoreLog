package io.github.fuadreza.storelog.repo

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.localdb.user.UserDao
import io.github.fuadreza.storelog.model.User

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class UserRepo(private val userDao: UserDao) {

    suspend fun loginUser(username: String, password: String): LiveData<User>{
        return userDao.getUser(username, password)
    }

}