package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource

import com.sayantanbanerjee.transactionmanagementapp.data.model.State

interface RemoteDataSource {
    fun saveStateToFirebase(state: State)
}
