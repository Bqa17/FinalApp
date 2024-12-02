package com.example.finalapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.ui.add.AddViewModel

class AddViewModelFactory(private val budgetDao: BudgetDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddViewModel(budgetDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
