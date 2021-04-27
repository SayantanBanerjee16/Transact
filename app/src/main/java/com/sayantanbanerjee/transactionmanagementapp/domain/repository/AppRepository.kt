package com.sayantanbanerjee.transactionmanagementapp.domain.repository

import androidx.lifecycle.LiveData
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import kotlinx.coroutines.flow.Flow

// Interface which defines all the use-cases of the project.
interface AppRepository {
    suspend fun saveRecord(record: Record)
    suspend fun updateRecordStatus(status: String, id: Int)
    fun fetchAcceptedSentAmountFromDB(): Flow<Int>
    fun fetchSumReceivedFromDB(): Flow<Int>
    fun getAllRecords() : Flow<List<Record>>
}
