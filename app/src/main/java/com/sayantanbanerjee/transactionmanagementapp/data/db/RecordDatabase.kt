package com.sayantanbanerjee.transactionmanagementapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sayantanbanerjee.transactionmanagementapp.data.model.Record

// Database class which holds all the entity classes, and build the database object.
@Database(
    entities = [Record::class],
    version = 1,
    exportSchema = false
)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun getRecordDao(): RecordDAO
}
