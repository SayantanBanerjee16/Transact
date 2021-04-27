package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecordsUseCase(private val appRepository: AppRepository) {
    fun execute(): Flow<List<Record>> {
        return appRepository.getAllRecords()
    }
}
