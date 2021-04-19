package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase

class TransactionViewModelFactory(
    private val saveRecordUseCase: SaveRecordUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(
            saveRecordUseCase
        ) as T
    }
}
