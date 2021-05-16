package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

// Use case for fetching the state from the firebase.
class GetStateFromFirebaseUseCase(private val appRepository: AppRepository) {
    fun execute() {
        appRepository.getStateFromFirebase()
    }
}
