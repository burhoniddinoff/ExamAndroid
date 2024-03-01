package com.example.examandroid.di

import com.example.examandroid.navigation.AppNavigationDispatcher
import com.example.examandroid.navigation.AppNavigationHandler
import com.example.examandroid.navigation.AppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindAppNavigator(impl: AppNavigationDispatcher): AppNavigator


    @[Binds Singleton]
    fun bindAppNavigationHandler(imp: AppNavigationDispatcher): AppNavigationHandler

}