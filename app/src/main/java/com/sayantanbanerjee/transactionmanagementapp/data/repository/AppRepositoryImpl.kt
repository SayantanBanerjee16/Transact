package com.sayantanbanerjee.transactionmanagementapp.data.repository

import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RecordLocalDataSource
import com.sayantanbanerjee.transactionmanagementapp.data.repository.DataSource.RemoteDataSource
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

// Class defining the functionality to the methods described in the [AppRepository.kt] interface in the Domain section
class AppRepositoryImpl(
    private val recordLocalDataSource: RecordLocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : AppRepository {
    override suspend fun saveRecord(record: Record) {
        recordLocalDataSource.saveRecordToDB(record)
    }

    override fun fetchAcceptedSentAmountFromDB(): Flow<Int> {
        return recordLocalDataSource.fetchAcceptedSentAmountFromDB()
    }

    override fun fetchAcceptedSentAmountFromDBByAParticularContact(phoneNumber: String): Flow<Int> {
        return recordLocalDataSource.fetchAcceptedSentAmountOfAParticularContact(phoneNumber)
    }

    override fun fetchAcceptedReceivedAmountFromDBByAParticularContact(phoneNumber: String): Flow<Int> {
        return recordLocalDataSource.getSumReceivedOfAParticularContact(phoneNumber)
    }

    override fun fetchSumReceivedFromDB(): Flow<Int> {
        return recordLocalDataSource.getSumReceivedFromDB()
    }

    override fun getAllRecords(): Flow<List<Record>> {
        return recordLocalDataSource.getAllRecords()
    }

    override fun getAllRecordsOfAParticularContact(phoneNumber: String): Flow<List<Record>> {
        return recordLocalDataSource.getAllRecordsOfAParticularContact(phoneNumber)
    }

    override fun saveStateToFirebase(state: State) {
        remoteDataSource.saveStateToFirebase(state)
    }

    override fun getStateFromFirebase() {
        remoteDataSource.fetchStateFromFirebase()
    }
}
