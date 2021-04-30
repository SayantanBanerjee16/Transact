package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase

class ScanningViewModelFactory(
    private val saveRecordUseCase: SaveRecordUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScanningViewModel(
            saveRecordUseCase
        ) as T
    }
}
