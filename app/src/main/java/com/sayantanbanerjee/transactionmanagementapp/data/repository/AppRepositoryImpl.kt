package com.sayantanbanerjee.transactionmanagementapp.data.repository

import androidx.lifecycle.LiveData
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

// Class defining the functionality to the methods described in the [AppRepository.kt] interface in the Domain section
class AppRepositoryImpl(
    private val recordLocalDataSource: RecordLocalDataSource
) : AppRepository {
    override suspend fun saveRecord(record: Record) {
        recordLocalDataSource.saveRecordToDB(record)
    }

    override suspend fun updateRecordStatus(status: String, id: Int) {
        recordLocalDataSource.updateRecordStatusToDB(status, id)
    }

    override fun fetchAcceptedSentAmountFromDB(): Flow<Int> {
        return recordLocalDataSource.fetchAcceptedSentAmountFromDB()
    }

    override fun fetchSumReceivedFromDB(): Flow<Int> {
        return recordLocalDataSource.getSumReceivedFromDB()
    }

    override fun getAllRecords(): Flow<List<Record>> {
        return recordLocalDataSource.getAllRecords()
    }
}
