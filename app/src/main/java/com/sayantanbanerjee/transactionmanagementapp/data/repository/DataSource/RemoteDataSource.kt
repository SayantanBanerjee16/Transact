package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource

import com.sayantanbanerjee.transactionmanagementapp.data.model.State

// Interface defining the methods to be performed in the remote database, i.e, firebase..
interface RemoteDataSource {
    fun saveStateToFirebase(state: State)
    fun fetchStateFromFirebase()
}
