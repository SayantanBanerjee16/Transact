package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource

import androidx.lifecycle.LiveData
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import kotlinx.coroutines.flow.Flow

// Interface defining the methods to be performed in the database.
interface RecordLocalDataSource {
    suspend fun saveRecordToDB(record: Record)
    suspend fun updateRecordStatusToDB(status: String, id: Int)
    fun fetchAcceptedSentAmountFromDB(): Flow<Int>
    fun getSumReceivedFromDB(): Flow<Int>
    fun getAllRecords(): Flow<List<Record>>
    fun getAllRecordsOfAParticularContact(phoneNumber: String): Flow<List<Record>>
    fun fetchAcceptedSentAmountOfAParticularContact(phoneNumber: String): Flow<Int>
    fun getSumReceivedOfAParticularContact(phoneNumber: String): Flow<Int>
}
