package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.GetAcceptedSumSentUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.UpdateRecordStatusUseCase
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
        updateRecordStatusUseCase: UpdateRecordStatusUseCase,
        getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase
    ): TransactionViewModelFactory {
        return TransactionViewModelFactory(
            saveRecordUseCase,
            updateRecordStatusUseCase,
            getAcceptedSumSentUseCase
        )
    }
}
