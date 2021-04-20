package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

class GetAcceptedSumSentUseCase(private val appRepository: AppRepository) {
    suspend fun execute(): Int {
        return appRepository.fetchAcceptedSentAmountFromDB()
    }
}
