package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import androidx.lifecycle.LiveData
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

// Use case for fetching the accepted sum sent by all the records in the database.
class GetAcceptedSumSentUseCase(private val appRepository: AppRepository) {
    fun execute(): Flow<Int> {
        return appRepository.fetchAcceptedSentAmountFromDB()
    }
}
