package com.sayantanbanerjee.transactionmanagementapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record

// DAO Interface which defines all the methods which queries SQL statements into the database,
// and the resultant required data is returned as required.
@Dao
interface RecordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: Record)

}
