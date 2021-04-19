package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
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
        saveRecordUseCase: SaveRecordUseCase
    ): TransactionViewModelFactory {
        return TransactionViewModelFactory(
            saveRecordUseCase
        )
    }
}
