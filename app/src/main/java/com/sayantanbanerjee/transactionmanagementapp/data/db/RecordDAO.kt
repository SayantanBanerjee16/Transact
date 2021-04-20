package com.sayantanbanerjee.transactionmanagementapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record

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

}
