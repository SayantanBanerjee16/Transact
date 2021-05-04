package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSourceImpl

import com.google.firebase.database.FirebaseDatabase
import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RemoteDataSource

class RemoteDataSourceImpl : RemoteDataSource {

    companion object {
        private const val KEY_USERS = "USERS"
    }

    override fun saveStateToFirebase(state: State) {
        val firebaseReference = FirebaseDatabase.getInstance("https://transaction-management-app-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        firebaseReference.child(KEY_USERS).child(state.phoneNumber).push().setValue(state)
    }
}
