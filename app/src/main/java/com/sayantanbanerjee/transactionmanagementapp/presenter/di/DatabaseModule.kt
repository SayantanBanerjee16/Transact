package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import android.app.Application
import androidx.room.Room
import com.sayantanbanerjee.transactionmanagementapp.data.db.RecordDAO
import com.sayantanbanerjee.transactionmanagementapp.data.db.RecordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Database module creates the object for the Record Database and the Record DAO.
// Hilt can automatically inject it into fields or constructor arguments where we want to use it.
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesNewsDatabase(app: Application): RecordDatabase {
        return Room.databaseBuilder(app, RecordDatabase::class.java, "record_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(recordDatabase: RecordDatabase): RecordDAO {
        return recordDatabase.getRecordDao()
    }

}
