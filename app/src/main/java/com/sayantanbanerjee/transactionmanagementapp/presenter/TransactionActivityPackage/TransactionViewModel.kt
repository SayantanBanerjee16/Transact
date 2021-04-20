package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.UpdateRecordStatusUseCase
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val updateRecordStatusUseCase: UpdateRecordStatusUseCase
) : ViewModel() {

    // save data to local database
    fun saveRecord() = viewModelScope.launch {
        val currentTimestamp = System.currentTimeMillis()
        val record = Record(
            0,
            "+919434792685",
            10,
            0, currentTimestamp.toString(),
            "INVITED"
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

}
