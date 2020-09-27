package io.github.fuadreza.storelog.repository

import io.github.fuadreza.storelog.database.dao.UserDao
import io.github.fuadreza.storelog.database.entity.User
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun loginUser(username: String, password: String): Boolean {
        try {
            val user = userDao.getUser(username, password)
            if (user.userName == username) {
                return true
            }
        } catch (e: Exception) {
            print(e)
        }
        return false
    }

    fun registerUser(username: String, password: String): Boolean {
        try {
            val user = User(0, username, password);
            userDao.insert(user)
            return true
        } catch (e: Exception) {
            print(e)
        }
        return false
    }

}