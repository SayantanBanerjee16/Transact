package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetAcceptedSumReceivedByAParticularContact(private val appRepository: AppRepository) {
    fun execute(phoneNumber: String): Flow<Int> {
        return appRepository.fetchAcceptedReceivedAmountFromDBByAParticularContact(phoneNumber)
    }
}
