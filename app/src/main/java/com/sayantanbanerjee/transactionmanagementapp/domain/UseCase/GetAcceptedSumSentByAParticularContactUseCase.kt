package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetAcceptedSumSentByAParticularContactUseCase(private val appRepository: AppRepository) {
    fun execute(phoneNumber: String): Flow<Int> {
        return appRepository.fetchAcceptedSentAmountFromDBByAParticularContact(phoneNumber)
    }
}
