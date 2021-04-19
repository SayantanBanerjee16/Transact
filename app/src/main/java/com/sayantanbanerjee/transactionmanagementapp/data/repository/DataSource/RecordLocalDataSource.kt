package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record

// Interface defining the methods to be performed in the database
interface RecordLocalDataSource {
    suspend fun saveRecordToDB(record: Record)
}
