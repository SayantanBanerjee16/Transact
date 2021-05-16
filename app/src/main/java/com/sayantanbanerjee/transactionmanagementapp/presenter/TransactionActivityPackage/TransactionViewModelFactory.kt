package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*

// View Model Factory class for the [TransactionViewModel.kt].
class TransactionViewModelFactory(
    private val getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase,
    private val getSumReceivedUseCase: GetSumReceivedUseCase,
    private val getAllRecordsUseCase: GetAllRecordsUseCase,
    private val getStateFromFirebaseUseCase: GetStateFromFirebaseUseCase,
    private val getAcceptedSumReceivedByAParticularContactUseCase: GetAcceptedSumReceivedByAParticularContactUseCase,
    private val getAcceptedSumSentByAParticularContactUseCase: GetAcceptedSumSentByAParticularContactUseCase,
    private val getAllRecordsOfAParticularContactUseCase: GetAllRecordsOfAParticularContactUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(
            getAcceptedSumSentUseCase,
            getSumReceivedUseCase,
            getAllRecordsUseCase,
            getStateFromFirebaseUseCase,
            getAcceptedSumReceivedByAParticularContactUseCase,
            getAcceptedSumSentByAParticularContactUseCase,
            getAllRecordsOfAParticularContactUseCase
        ) as T
    }
}
