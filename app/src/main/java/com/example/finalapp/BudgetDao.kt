package com.example.finalapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BudgetDao {
    @Insert
    suspend fun insert(budget: BudgetEntity)

    @Query("SELECT * FROM budget_table")
    suspend fun getAllBudgets(): List<BudgetEntity>
}
