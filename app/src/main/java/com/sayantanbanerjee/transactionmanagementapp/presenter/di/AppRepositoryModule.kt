package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.data.repository.AppRepositoryImpl
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RemoteDataSource
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// This Module class provide dependency injection for building the,
// AppRepositoryImpl(data->repository) which is called from the Use Cases.
@InstallIn(SingletonComponent::class)
@Module
class AppRepositoryModule {

    @Singleton
    @Provides
    fun providesAppRepository(
        recordLocalDataSource: RecordLocalDataSource,
        remoteDataSource: RemoteDataSource
    ): AppRepository {
        return AppRepositoryImpl(recordLocalDataSource, remoteDataSource)
    }

}
