package com.example.doingobject.model.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// ProductEntity.kt
@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "quantity") val quantity: Int
)

