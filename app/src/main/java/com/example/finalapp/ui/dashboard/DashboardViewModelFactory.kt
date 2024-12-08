package com.example.finalapp.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.BudgetDao

class DashboardViewModelFactory(private val budgetDao: BudgetDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(budgetDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
