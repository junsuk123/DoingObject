package com.example.doingobject.model.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// ProductEntity.kt
@Entity(tableName = "entry_table")
data class EntryEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "quantity") val quantity: Int
)


