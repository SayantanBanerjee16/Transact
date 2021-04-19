package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val saveRecordUseCase: SaveRecordUseCase
) : ViewModel() {

    // save data to local database
    fun saveRecord() = viewModelScope.launch {
        val currentTimestamp = System.currentTimeMillis()
        val record = Record(
            1,
            "+919434792685",
            100,
            1, currentTimestamp.toString(),
            "INVITED"
        )
        saveRecordUseCase.execute(record)
    }

}
