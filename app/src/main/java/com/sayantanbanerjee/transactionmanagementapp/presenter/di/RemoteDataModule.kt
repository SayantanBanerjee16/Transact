package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RemoteDataSource
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl()
    }

}
