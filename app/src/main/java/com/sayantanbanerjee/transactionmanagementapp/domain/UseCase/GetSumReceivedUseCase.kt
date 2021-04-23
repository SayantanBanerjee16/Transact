package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetSumReceivedUseCase(private val appRepository: AppRepository) {
    fun execute(): Flow<Int> {
        return appRepository.fetchSumReceivedFromDB()
    }
}
