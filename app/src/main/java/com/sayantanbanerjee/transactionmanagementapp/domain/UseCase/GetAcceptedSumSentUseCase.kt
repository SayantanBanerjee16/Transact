package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import androidx.lifecycle.LiveData
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetAcceptedSumSentUseCase(private val appRepository: AppRepository) {
    fun execute(): Flow<Int> {
        return appRepository.fetchAcceptedSentAmountFromDB()
    }
}
