package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

class GetStateFromFirebaseUseCase(private val appRepository: AppRepository) {
    fun execute() {
        appRepository.getStateFromFirebase()
    }
}
