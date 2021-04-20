package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.GetAcceptedSumSentUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.UpdateRecordStatusUseCase

class TransactionViewModelFactory(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val updateRecordStatusUseCase: UpdateRecordStatusUseCase,
    private val getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(
            saveRecordUseCase,
            updateRecordStatusUseCase,
            getAcceptedSumSentUseCase
        ) as T
    }
}
