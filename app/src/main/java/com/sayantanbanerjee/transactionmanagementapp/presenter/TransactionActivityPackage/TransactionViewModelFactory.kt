package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*

class TransactionViewModelFactory(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase,
    private val getSumReceivedUseCase: GetSumReceivedUseCase,
    private val getAllRecordsUseCase: GetAllRecordsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(
            saveRecordUseCase,
            getAcceptedSumSentUseCase,
            getSumReceivedUseCase,
            getAllRecordsUseCase
        ) as T
    }
}
