package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.*
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*
import kotlinx.coroutines.flow.collect

class TransactionViewModel(
    private val getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase,
    private val getSumReceivedUseCase: GetSumReceivedUseCase,
    private val getAllRecordsUseCase: GetAllRecordsUseCase,
    private val getStateFromFirebaseUseCase: GetStateFromFirebaseUseCase,
    private val getAcceptedSumReceivedByAParticularContactUseCase: GetAcceptedSumReceivedByAParticularContactUseCase,
    private val getAcceptedSumSentByAParticularContactUseCase: GetAcceptedSumSentByAParticularContactUseCase,
    private val getAllRecordsOfAParticularContactUseCase: GetAllRecordsOfAParticularContactUseCase

) : ViewModel() {

    fun getSumSentAcceptedValue() = liveData {
        getAcceptedSumSentUseCase.execute().collect {
            emit(it)
        }
    }

    fun getSumReceivedValue() = liveData {
        getSumReceivedUseCase.execute().collect {
            emit(it)
        }
    }

    fun getAllRecords() = liveData {
        getAllRecordsUseCase.execute().collect {
            emit(it)
        }
    }

    fun getStateFromFirebase() {
        getStateFromFirebaseUseCase.execute()
    }

    fun getAllTransactionsRecordOfAParticularContact(phoneNumber: String) = liveData {
        getAllRecordsOfAParticularContactUseCase.execute(phoneNumber).collect {
            emit(it)
        }
    }
    
    fun getAcceptedSumSentOfAParticularContact(phoneNumber: String) = liveData {
        getAcceptedSumSentByAParticularContactUseCase.execute(phoneNumber).collect {
            emit(it)
        }
    }

    fun getAcceptedSumReceivedOfAParticularContact(phoneNumber: String) = liveData {
        getAcceptedSumReceivedByAParticularContactUseCase.execute(phoneNumber).collect {
            emit(it)
        }
    }


}
