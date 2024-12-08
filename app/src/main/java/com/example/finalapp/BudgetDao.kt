package com.example.finalapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert
    suspend fun insert(budget: BudgetEntity)

    @Query("SELECT * FROM budget_table")
    fun getAllBudgets(): Flow<List<BudgetEntity>>
}
