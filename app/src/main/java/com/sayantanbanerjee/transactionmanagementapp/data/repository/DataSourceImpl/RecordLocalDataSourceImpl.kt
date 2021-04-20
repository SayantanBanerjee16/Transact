package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSourceImpl

import com.sayantanbanerjee.transactionmanagementapp.data.db.RecordDAO
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource

// Class defining the functionality to the methods described in the [RecordLocalDataSource.kt] interface
class RecordLocalDataSourceImpl(
    private val recordDAO: RecordDAO
) : RecordLocalDataSource {

    override suspend fun saveRecordToDB(record: Record) {
        recordDAO.insert(record)
    }

    override suspend fun updateRecordStatusToDB(status: String, id: Int) {
        if(status == "ACCEPTED"){
            recordDAO.updateRecordAsAccepted(id)
        }else{
            recordDAO.updateRecordAsRejected(id)
        }
    }
}
