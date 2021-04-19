package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

// Use Case To Save a New Record in the database.
class SaveRecordUseCase(private val appRepository: AppRepository) {
    suspend fun execute(record: Record): Unit {
        appRepository.saveRecord(record)
    }
}
