package com.example.finalapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.BudgetDatabase
import com.example.finalapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PurchaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val budgetDao = BudgetDatabase.getInstance(requireContext()).budgetDao()
        val factory = ListViewModelFactory(budgetDao)
        val listViewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = PurchaseAdapter(emptyList())
        binding.recyclerSectionedList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ListFragment.adapter
        }

        listViewModel.budgets.observe(viewLifecycleOwner) { purchases ->
            adapter.updateData(purchases)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
