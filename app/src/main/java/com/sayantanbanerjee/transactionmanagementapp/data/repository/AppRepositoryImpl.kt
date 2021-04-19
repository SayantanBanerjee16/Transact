package com.sayantanbanerjee.transactionmanagementapp.data.repository

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

// Class defining the functionality to the methods described in the [AppRepository.kt] interface in the Domain section
class AppRepositoryImpl(
    private val recordLocalDataSource: RecordLocalDataSource
) : AppRepository {
    override suspend fun saveRecord(record: Record) {
        recordLocalDataSource.saveRecordToDB(record)
    }
}
