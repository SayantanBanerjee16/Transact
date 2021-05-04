package com.sayantanbanerjee.transactionmanagementapp.domain.UseCase

import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository

class SaveStateToFirebaseUseCase(private val appRepository: AppRepository) {
    fun execute(state: State): Unit {
        appRepository.saveStateToFirebase(state)
    }
}
