package com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase

// View Model Factory class for the [AddRecordViewModel.kt].
class AddRecordViewModelFactory(
    private val saveRecordUseCase: SaveRecordUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddRecordViewModel(
            saveRecordUseCase
        ) as T
    }
}