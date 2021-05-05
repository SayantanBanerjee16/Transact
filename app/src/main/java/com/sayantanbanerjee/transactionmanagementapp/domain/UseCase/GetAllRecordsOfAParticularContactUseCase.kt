package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecordsOfAParticularContactUseCase(private val appRepository: AppRepository) {
    fun execute(phoneNumber: String): Flow<List<Record>> {
        return appRepository.getAllRecordsOfAParticularContact(phoneNumber)
    }
}
