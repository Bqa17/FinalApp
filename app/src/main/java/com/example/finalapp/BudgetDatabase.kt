package com.example.finalapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [BudgetEntity::class], version = 3, exportSchema = false)
abstract class BudgetDatabase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao

    companion object {
        @Volatile
        private var INSTANCE: BudgetDatabase? = null

        private val MIGRATION_1_2 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Add the new column
                db.execSQL("ALTER TABLE budget_table ADD COLUMN date INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getInstance(context: Context): BudgetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetDatabase::class.java,
                    "Budget_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }





}

