package com.example.finalapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.BudgetDao
import com.example.finalapp.BudgetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val budgetDao: BudgetDao) : ViewModel() {

    fun addBudget(amount: String, category: String) {
        val budgetEntity = BudgetEntity(
            amount = amount,
            category = CharCategory.valueOf(category).toString()
        )
        viewModelScope.launch(Dispatchers.IO) {
            budgetDao.insert(budgetEntity)
        }
    }
}
