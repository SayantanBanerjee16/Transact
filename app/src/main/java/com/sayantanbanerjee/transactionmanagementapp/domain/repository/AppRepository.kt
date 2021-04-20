package com.sayantanbanerjee.transactionmanagementapp.domain.repository

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record

// Interface which defines all the use-cases of the project.
interface AppRepository {
    suspend fun saveRecord(record: Record)
    suspend fun updateRecordStatus(status: String, id: Int)
    suspend fun fetchAcceptedSentAmountFromDB(): Int
}
