package com.example.finalapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.finalapp.BudgetDao
import com.example.finalapp.BudgetEntity

class DashboardViewModel(budgetDao: BudgetDao) : ViewModel() {
    val recentTransactions: LiveData<List<BudgetEntity>> = budgetDao.getAllBudgets().asLiveData()
}
