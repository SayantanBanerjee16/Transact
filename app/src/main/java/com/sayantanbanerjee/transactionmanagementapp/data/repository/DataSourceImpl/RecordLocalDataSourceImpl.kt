package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSourceImpl

import androidx.lifecycle.LiveData
import com.sayantanbanerjee.transactionmanagementapp.data.db.RecordDAO
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource
import kotlinx.coroutines.flow.Flow

// Class defining the functionality to the methods described in the [RecordLocalDataSource.kt] interface
class RecordLocalDataSourceImpl(
    private val recordDAO: RecordDAO
) : RecordLocalDataSource {

    override suspend fun saveRecordToDB(record: Record) {
        recordDAO.insert(record)
    }

    override suspend fun updateRecordStatusToDB(status: String, id: Int) {
        if (status == "ACCEPTED") {
            recordDAO.updateRecordAsAccepted(id)
        } else {
            recordDAO.updateRecordAsRejected(id)
        }
    }

    override fun fetchAcceptedSentAmountFromDB(): Flow<Int> {
        return recordDAO.getAcceptedSumSent()
    }

    override fun getSumReceivedFromDB(): Flow<Int> {
        return recordDAO.getSumReceived()
    }

    override fun getAllRecords(): Flow<List<Record>> {
        return recordDAO.getAllRecords()
    }

    override fun getAllRecordsOfAParticularContact(phoneNumber: String): Flow<List<Record>> {
        return recordDAO.getAllRecordsOfParticularContact(phoneNumber)
    }

    override fun fetchAcceptedSentAmountOfAParticularContact(phoneNumber: String): Flow<Int> {
        return recordDAO.getAcceptedSumSentOfAParticularContact(phoneNumber)
    }

    override fun getSumReceivedOfAParticularContact(phoneNumber: String): Flow<Int> {
        return recordDAO.getSumReceivedOfAParticularContact(phoneNumber)
    }
}
