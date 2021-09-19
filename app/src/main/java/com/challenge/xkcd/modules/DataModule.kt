package com.challenge.xkcd.modules

import android.content.Context
import androidx.room.Room
import com.challenge.xkcd.dataLayer.local.ComicDAO
import com.challenge.xkcd.dataLayer.local.ComicsRoomDatabase
import com.challenge.xkcd.dataLayer.remote.RemoteService
import com.challenge.xkcd.dataLayer.remote.ServiceGenerator
import com.challenge.xkcd.domainLayer.base.UseCaseHandler
import com.challenge.xkcd.domainLayer.base.UseCaseScheduler
import com.challenge.xkcd.domainLayer.base.UseCaseThreadPoolScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteService(): RemoteService {
        return ServiceGenerator().createService(RemoteService::class.java)
    }

    /**
     * binds handlers
     * */
    @Singleton
    @Provides
    fun provideUseCaseHandler(useCaseScheduler: UseCaseScheduler): UseCaseHandler {
        return UseCaseHandler(useCaseScheduler)
    }

    @Singleton
    @Provides
    fun provideUseCaseScheduler(): UseCaseScheduler = UseCaseThreadPoolScheduler()

    @Singleton
    @Provides
    fun provideComicsRoomDatabase(@ApplicationContext appContext: Context): ComicDAO {
        return Room.databaseBuilder(
            appContext.applicationContext,
            ComicsRoomDatabase::class.java,
            "comics.db"
        ).build().comicDAODao()
    }
}