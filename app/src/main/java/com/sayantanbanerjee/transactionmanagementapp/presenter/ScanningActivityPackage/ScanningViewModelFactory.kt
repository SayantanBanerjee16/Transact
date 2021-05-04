package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveStateToFirebaseUseCase

class ScanningViewModelFactory(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val saveStateToFirebaseUseCase: SaveStateToFirebaseUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScanningViewModel(
            saveRecordUseCase,
            saveStateToFirebaseUseCase
        ) as T
    }
}
