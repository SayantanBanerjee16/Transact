package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

// Use case for saving the transaction state to the firebase.
class SaveStateToFirebaseUseCase(private val appRepository: AppRepository) {
    fun execute(state: State): Unit {
        appRepository.saveStateToFirebase(state)
    }
}
