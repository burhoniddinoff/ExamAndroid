package com.example.examandroid.di

import com.example.examandroid.domain.AppRepository
import com.example.examandroid.domain.impl.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {

    @Binds
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository

}
