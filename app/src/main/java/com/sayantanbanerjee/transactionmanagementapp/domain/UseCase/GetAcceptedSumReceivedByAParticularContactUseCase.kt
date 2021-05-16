package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

// Use case for fetching the accepted sum received by a particular contact.
class GetAcceptedSumReceivedByAParticularContactUseCase(private val appRepository: AppRepository) {
    fun execute(phoneNumber: String): Flow<Int> {
        return appRepository.fetchAcceptedReceivedAmountFromDBByAParticularContact(phoneNumber)
    }
}
