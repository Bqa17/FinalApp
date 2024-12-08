package com.example.finalapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_table")
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val amount: String,
    @ColumnInfo val category: String,
    @ColumnInfo val date: String // Store date as a timestamp
)
