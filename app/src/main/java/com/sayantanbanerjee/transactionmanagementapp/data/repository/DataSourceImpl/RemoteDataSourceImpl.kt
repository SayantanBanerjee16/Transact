package com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSourceImpl

import android.content.SharedPreferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RemoteDataSourceImpl(
    private val sharedPreferences: SharedPreferences,
    private val recordLocalDataSource: RecordLocalDataSource
) : RemoteDataSource {

    companion object {
        private const val KEY_USERS = "USERS"
    }

    override fun saveStateToFirebase(state: State) {
        val firebaseReference =
            FirebaseDatabase.getInstance("https://transaction-management-app-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        firebaseReference.child(KEY_USERS).child(state.phoneNumber!!).push().setValue(state)
    }

    override fun fetchStateFromFirebase() {

        val firebaseReference =
            FirebaseDatabase.getInstance("https://transaction-management-app-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        val phoneNumber = AppPreferenceHelper(sharedPreferences).getUserMobileNumber()
        val databaseReference = firebaseReference.child(KEY_USERS).child(phoneNumber!!)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val curState: State? = snapshot.getValue(State::class.java)
                        CoroutineScope(Dispatchers.IO).launch {
                            recordLocalDataSource.updateRecordStatusToDB(
                                curState!!.status!!,
                                curState.id!!.toInt()
                            )
                        }
                        snapshot.ref.removeValue()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}
