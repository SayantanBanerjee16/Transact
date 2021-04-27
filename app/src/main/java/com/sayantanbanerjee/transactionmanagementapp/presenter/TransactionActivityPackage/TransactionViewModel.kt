package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.*
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val saveRecordUseCase: SaveRecordUseCase,
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
            1, currentTimestamp.toString(),
            "ACCEPTED"
        )
        saveRecordUseCase.execute(record)
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
