package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

// Use case for fetching the accepted sum received by all the records in the database.
class GetSumReceivedUseCase(private val appRepository: AppRepository) {
    fun execute(): Flow<Int> {
        return appRepository.fetchSumReceivedFromDB()
    }
}
