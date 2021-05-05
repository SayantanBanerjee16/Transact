package com.sayantanbanerjee.transactionmanagementapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record
import kotlinx.coroutines.flow.Flow

// DAO Interface which defines all the methods which queries SQL statements into the database,
// and the resultant required data is returned as required.
@Dao
interface RecordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: Record)

    @Query("UPDATE records SET record_status = 'ACCEPTED' WHERE record_id = :tid")
    suspend fun updateRecordAsAccepted(tid: Int)

    @Query("UPDATE records SET record_status = 'REJECTED' WHERE record_id = :tid")
    suspend fun updateRecordAsRejected(tid: Int)

    @Query("SELECT COALESCE(sum(COALESCE(record_amount_involved,0)), 0) FROM records WHERE record_parity = 1 AND record_status = 'ACCEPTED' ")
    fun getAcceptedSumSent(): Flow<Int>

    @Query("SELECT COALESCE(sum(COALESCE(record_amount_involved,0)), 0) FROM records WHERE record_parity = 0 AND record_status = 'ACCEPTED' ")
    fun getSumReceived(): Flow<Int>

    @Query("SELECT * FROM records ORDER BY record_timestamp DESC")
    fun getAllRecords(): Flow<List<Record>>

    @Query("SELECT * FROM records WHERE record_receiver_contact_number = :tPhoneNumber ORDER BY record_timestamp DESC")
    fun getAllRecordsOfParticularContact(tPhoneNumber : String): Flow<List<Record>>
}
