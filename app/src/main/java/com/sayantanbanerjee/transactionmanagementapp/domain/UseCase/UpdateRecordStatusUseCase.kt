package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

// Use Case To Update State of the Record in the Database
class UpdateRecordStatusUseCase(private val appRepository: AppRepository) {
    suspend fun execute(status: String, id: Int): Unit {
        appRepository.updateRecordStatus(status, id)
    }
}
