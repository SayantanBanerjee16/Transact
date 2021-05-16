package com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import kotlinx.coroutines.launch

// View Model Class for the [AddRecordActivity.kt].
class AddRecordViewModel(private val saveRecordUseCase: SaveRecordUseCase) : ViewModel() {

    // save data to local database after generating the QR code.
    fun saveRecord(receiverContactNumber: String, amountInvolved: Int, parity: Int) =
        viewModelScope.launch {
            val currentTimestamp = System.currentTimeMillis()
            val record = Record(
                0,
                receiverContactNumber,
                amountInvolved,
                parity, currentTimestamp.toString(),
                "INVITED"
            )
            saveRecordUseCase.execute(record)
        }

}
