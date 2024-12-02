package com.example.finalapp

import android.app.Application

class BudgetApplication : Application() {
    val db by lazy { BudgetDatabase.getInstance(this) }
}