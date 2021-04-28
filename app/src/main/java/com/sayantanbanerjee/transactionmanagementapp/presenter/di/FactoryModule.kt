package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*
import com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage.AddRecordViewModel
import com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage.AddRecordViewModelFactory
import com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage.TransactionViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    // provided the view model factory class for the TransactionViewModelFactory
    @Singleton
    @Provides
    fun providesTransactionViewModelFactory(
        saveRecordUseCase: SaveRecordUseCase,
        getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase,
        getSumReceivedUseCase: GetSumReceivedUseCase,
        getAllRecordsUseCase: GetAllRecordsUseCase
    ): TransactionViewModelFactory {
        return TransactionViewModelFactory(
            saveRecordUseCase,
            getAcceptedSumSentUseCase,
            getSumReceivedUseCase,
            getAllRecordsUseCase
        )
    }

    // provided the view model factory class for the AddRecordViewModelFactory
    @Singleton
    @Provides
    fun providesAddRecordViewModelFactory(
        saveRecordUseCase: SaveRecordUseCase
    ): AddRecordViewModelFactory {
        return AddRecordViewModelFactory(
            saveRecordUseCase
        )
    }
}
