package com.example.examandroid.navigation

import com.example.examandroid.navigation.AppNavigation
import kotlinx.coroutines.flow.Flow

interface AppNavigationHandler {
    val buffer : Flow<AppNavigation>
}