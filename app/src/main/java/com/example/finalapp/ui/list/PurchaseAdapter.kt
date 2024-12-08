package com.example.finalapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.BudgetEntity
import com.example.finalapp.IncomeCategory
import com.example.finalapp.R
import com.example.finalapp.databinding.ListItemBinding

class PurchaseAdapter(private var purchases: List<BudgetEntity>) :
    RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(purchases.reversed()[position])
    }

    override fun getItemCount(): Int = purchases.size

    fun updateData(newPurchases: List<BudgetEntity>) {
        purchases = newPurchases
        notifyDataSetChanged()
    }

    inner class PurchaseViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(budget: BudgetEntity) {
            binding.textTransactionName.text = budget.category
            binding.textTransactionDate.text =
                budget.date // Assuming `date` is properly formatted.


            val amount = budget.amount.toFloat() // Assuming amount is a String, converting to Float for formatting
            val formattedAmount = if (isIncomeCategory(budget.category)) {
                binding.textTransactionAmount.setTextColor(binding.root.context.getColor(R.color.green))
                binding.root.context.getString(R.string.amount_format, amount)
            } else {
                binding.textTransactionAmount.setTextColor(binding.root.context.getColor(R.color.red))
                binding.root.context.getString(R.string.amount_format_negative, amount)
            }
            binding.textTransactionAmount.text = formattedAmount

        }

        private fun isIncomeCategory(category: String): Boolean {
            return try {
                IncomeCategory.valueOf(category) // Tries to match the category to an IncomeCategory
                true
            } catch (e: IllegalArgumentException) {
                false // If it can't be matched to an IncomeCategory, it's an expense
            }
        }
    }
}
