package com.example.finalapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.finalapp.BudgetDao
import com.example.finalapp.BudgetEntity

data class Purchase(
    val name: String,
    val date: String,
    val amount: String
)

class ListViewModel(budgetDao: BudgetDao) : ViewModel() {
    val budgets: LiveData<List<BudgetEntity>> = budgetDao.getAllBudgets().asLiveData()
}

class ListViewModelFactory(private val budgetDao: BudgetDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(budgetDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
