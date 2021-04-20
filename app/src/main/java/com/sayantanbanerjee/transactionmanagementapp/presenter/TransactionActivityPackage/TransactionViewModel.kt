package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.*
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.GetAcceptedSumSentUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.UpdateRecordStatusUseCase
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val updateRecordStatusUseCase: UpdateRecordStatusUseCase,
    private val getAcceptedSumSentUseCase: GetAcceptedSumSentUseCase
) : ViewModel() {

     var acceptSumSent : MutableLiveData<Int> = MutableLiveData()

    // save data to local database
    fun saveRecord() = viewModelScope.launch {
        val currentTimestamp = System.currentTimeMillis()
        val record = Record(
            0,
            "+919434792685",
            20,
            1, currentTimestamp.toString(),
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

    fun getSumSentAcceptedValue()  = viewModelScope.launch{
        acceptSumSent.postValue(getAcceptedSumSentUseCase.execute())
    }


}
