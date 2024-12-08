package com.example.finalapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.BudgetDatabase
import com.example.finalapp.databinding.FragmentDashboardBinding
import com.example.finalapp.ui.list.PurchaseAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var transactionAdapter: PurchaseAdapter

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val budgetDao = BudgetDatabase.getInstance(requireContext()).budgetDao()
        dashboardViewModel = ViewModelProvider(this, DashboardViewModelFactory(budgetDao))[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView with empty list, so it can be updated later
        transactionAdapter = PurchaseAdapter(emptyList())  // Empty list initially

        binding.recyclerRecentTransactions.layoutManager = LinearLayoutManager(requireContext())

        // Observe the recent transactions
        dashboardViewModel.recentTransactions.observe(viewLifecycleOwner) { transactions ->
            Log.d("DashboardFragment", "Transactions observed: $transactions")
            // Update the adapter data with the most recent transactions
            transactionAdapter.updateData(transactions.reversed().take(4))  // Show only the 4 most recent transactions

        }
        binding.recyclerRecentTransactions.adapter = transactionAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
