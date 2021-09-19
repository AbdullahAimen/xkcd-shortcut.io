package com.challenge.xkcd.di

import com.challenge.xkcd.dataLayer.dataSource.ComicsDataSource
import com.challenge.xkcd.dataLayer.dataSource.ComicsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImplementerModule {
    @Binds
    @Singleton
    abstract fun provideComicDataSourceImpl(mComicDataSourceImpl: ComicsDataSourceImpl): ComicsDataSource

}