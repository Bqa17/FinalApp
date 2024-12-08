package com.example.finalapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.BudgetDao
import com.example.finalapp.BudgetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddViewModel(private val budgetDao: BudgetDao) : ViewModel() {

    fun addBudget(amount: String, category: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val budgetEntity = BudgetEntity(
            amount = amount,
            category = category,
            date = currentDate // Pass the timestamp to the date field
        )
        viewModelScope.launch(Dispatchers.IO) {
            budgetDao.insert(budgetEntity)
        }
    }
}
