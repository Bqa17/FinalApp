package com.example.finalapp

data class DisplayBudget(
    val id: Long,
    val amount: Int,
    val category: CharCategory,
) : java.io.Serializable