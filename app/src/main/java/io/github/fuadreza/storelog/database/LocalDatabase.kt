package io.github.fuadreza.storelog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.fuadreza.storelog.database.dao.SupplyDao
import io.github.fuadreza.storelog.database.dao.UserDao
import io.github.fuadreza.storelog.database.entity.Supply
import io.github.fuadreza.storelog.database.entity.User
import io.github.fuadreza.storelog.utils.MyConstants

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@Database(entities = [User::class, Supply::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun supplyDao(): SupplyDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(
            context: Context
        ): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    MyConstants.DB.NAME
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}