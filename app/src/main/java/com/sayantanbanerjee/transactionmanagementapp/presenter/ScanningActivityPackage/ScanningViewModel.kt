package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveRecordUseCase
import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.SaveStateToFirebaseUseCase
import kotlinx.coroutines.launch

// View Model Class for the [ScanningActivity.kt].
class ScanningViewModel(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val saveStateToFirebaseUseCase: SaveStateToFirebaseUseCase
) : ViewModel() {

    // save data to local database
    fun saveRecord(receiverContactNumber: String, amountInvolved: Int, parity: Int, state: String) =
        viewModelScope.launch {
            val currentTimestamp = System.currentTimeMillis()
            val record = Record(
                0,
                receiverContactNumber,
                amountInvolved,
                parity, currentTimestamp.toString(),
                state
            )
            saveRecordUseCase.execute(record)
        }

    fun saveStateToFirebase(state: State) {
        saveStateToFirebaseUseCase.execute(state)
    }

}
