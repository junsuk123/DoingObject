package com.example.doingobject.model.entry

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// EntryDao.kt
@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entries: List<EntryEntity>) // 여러 개의 엔트리 삽입

    @Query("SELECT * FROM entry_table")
    suspend fun getAllEntries(): List<EntryEntity>
}

