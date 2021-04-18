package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// This Module class provide dependency injection for building the shared preference.
// We can then inject it into a local field in the activity and use as we please.
@Module
@InstallIn(SingletonComponent::class)
class AppPreferenceModule {

    @Provides
    @Singleton
    fun providesSharedPreference(application: Application) : SharedPreferences {
        return application.getSharedPreferences("com.sayantanbanerjee.transactionmanagementapp", Context.MODE_PRIVATE)
    }

}
