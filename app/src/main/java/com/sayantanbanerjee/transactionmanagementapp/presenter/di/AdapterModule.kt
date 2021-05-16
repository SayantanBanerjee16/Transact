package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage.TransactionAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// AdapterModule creates the dependency injection for the adapters defined in the Presenter section.
@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesTransactionAdapter() : TransactionAdapter {
        return TransactionAdapter()
    }

}
