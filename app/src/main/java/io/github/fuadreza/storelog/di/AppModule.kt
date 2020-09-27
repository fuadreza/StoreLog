package io.github.fuadreza.storelog.di

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.fuadreza.storelog.database.LocalDatabase
import io.github.fuadreza.storelog.database.dao.SupplyDao
import io.github.fuadreza.storelog.database.dao.UserDao
import io.github.fuadreza.storelog.repository.SupplyRepository
import io.github.fuadreza.storelog.repository.UserRepository
import io.github.fuadreza.storelog.view.login.LoginActivity
import kotlinx.coroutines.CoroutineScope

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideSupplyDao(@ApplicationContext appContext: Context): SupplyDao {
        return LocalDatabase.getDatabase(appContext).supplyDao()
    }

    @Provides
    fun provideSupplyRepo(supplyDao: SupplyDao) = SupplyRepository(supplyDao)

    @Provides
    fun provideUserDao(@ApplicationContext appContext: Context): UserDao {
        return LocalDatabase.getDatabase(appContext).userDao()
    }

    @Provides
    fun provideUserRepo(userDao: UserDao) = UserRepository(userDao)

}