package io.github.fuadreza.storelog.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.fuadreza.storelog.database.LocalDatabase
import io.github.fuadreza.storelog.database.dao.SupplyDao
import io.github.fuadreza.storelog.repository.SupplyRepository
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

}