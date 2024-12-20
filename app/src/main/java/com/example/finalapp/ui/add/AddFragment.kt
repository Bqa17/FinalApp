package com.example.finalapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.AddViewModelFactory
import com.example.finalapp.BudgetApplication
import com.example.finalapp.ExpenseCategory
import com.example.finalapp.IncomeCategory
import com.example.finalapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var addViewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get BudgetDao from Application
        val budgetDao = (requireActivity().application as BudgetApplication).db.budgetDao()

        // Use ViewModelFactory
        val factory = AddViewModelFactory(budgetDao)
        addViewModel = ViewModelProvider(this, factory)[AddViewModel::class.java]

        // Setup spinners and button actions
        setupSpinners()
        setupButtons()

        return root
    }

    private fun setupSpinners() {
        // Spinner setup for expense categories
        val expenseCategories = ExpenseCategory.entries.map { it.name }
        val expenseAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, expenseCategories)
        expenseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerExpenseCategory.adapter = expenseAdapter

        // Spinner setup for income categories
        val incomeCategories = IncomeCategory.entries.map { it.name }
        val incomeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, incomeCategories)
        incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIncomeCategory.adapter = incomeAdapter
    }


    private fun setupButtons() {
        binding.buttonAddExpense.setOnClickListener {
            val amount = binding.editAddExpenseAmount.text.toString()
            val category = binding.spinnerExpenseCategory.selectedItem?.toString() ?: ""
            if (amount.isNotBlank()) {
                addViewModel.addBudget(amount, category)
                Toast.makeText(requireContext(), "Expense added", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonAddIncome.setOnClickListener {
            val amount = binding.editAddIncomeAmount.text.toString()
            val category = binding.spinnerIncomeCategory.selectedItem?.toString() ?: ""
            if (amount.isNotBlank()) {
                addViewModel.addBudget(amount, category)
                Toast.makeText(requireContext(), "Income added", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        binding.editAddExpenseAmount.text.clear()
        binding.editAddIncomeAmount.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
