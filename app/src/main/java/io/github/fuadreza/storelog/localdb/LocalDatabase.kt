package io.github.fuadreza.storelog.localdb

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.fuadreza.storelog.localdb.supply.SupplyDao
import io.github.fuadreza.storelog.localdb.user.UserDao
import io.github.fuadreza.storelog.model.Supply
import io.github.fuadreza.storelog.model.User
import io.github.fuadreza.storelog.utils.MyConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@Database(entities = [User::class, Supply::class], version = 1, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun supplyDao(): SupplyDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LocalDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    MyConstants.DB.NAME
                )
                    .addCallback(StoreDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class StoreDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database ->
                scope.launch {
                    populateDatabase(database.userDao())
                    populateSupply(database.supplyDao())
                }
            }
        }

        suspend fun populateDatabase(userDao: UserDao){

            userDao.deleteAll()

            var user = User(0,userName = "admin1", passWord = "123456")
            userDao.insert(user)
            user = User(1,userName = "admin2", passWord = "123456")
            userDao.insert(user)
            user = User(2,userName = "admin3", passWord = "123456")
            userDao.insert(user)
            user = User(3,userName = "admin4", passWord = "123456")
            userDao.insert(user)
            user = User(4,userName = "admin5", passWord = "123456")
            userDao.insert(user)
        }

        suspend fun populateSupply(supplyDao: SupplyDao){

            /*var supply = Supply(0, "book", 1, "Indo", "2009")
            supplyDao.insert(supply)
            supply = Supply(1, "drink", 1, "Indo", "2009")
            supplyDao.insert(supply)*/
        }
    }

}