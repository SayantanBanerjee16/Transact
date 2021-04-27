package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.*
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val updateRecordStatusUseCase: UpdateRecordStatusUseCase,
    private val getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase,
    private val getSumReceivedUseCase: GetSumReceivedUseCase,
    private val getAllRecordsUseCase: GetAllRecordsUseCase
) : ViewModel() {

     var acceptSumSent : MutableLiveData<Int> = MutableLiveData()

    // save data to local database
    fun saveRecord() = viewModelScope.launch {
        val currentTimestamp = System.currentTimeMillis()
        val record = Record(
            0,
            "+919434792685",
            15,
            0, currentTimestamp.toString(),
            "ACCEPTED"
        )
        saveRecordUseCase.execute(record)
    }

    // update transaction as accepted to local database
    fun updateAsAccepted() = viewModelScope.launch {
        //   updateRecordStatusUseCase.execute("ACCEPTED", 6)
    }

    // update transaction as rejected to local database
    fun updateAsReject() = viewModelScope.launch {
        //  updateRecordStatusUseCase.execute("REJECTED", 7)
    }

    fun getSumSentAcceptedValue()  = liveData {
        getAcceptedSumSentUseCase.execute().collect {
            emit(it)
        }
    }

    fun getSumReceivedValue()  = liveData {
        getSumReceivedUseCase.execute().collect {
            emit(it)
        }
    }

    fun getAllRecords()  = liveData {
        getAllRecordsUseCase.execute().collect {
            emit(it)
        }
    }


}
