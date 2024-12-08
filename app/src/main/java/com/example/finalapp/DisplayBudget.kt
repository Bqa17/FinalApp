package com.example.finalapp

import java.io.Serializable

data class DisplayBudget(
    val id: Long,
    val amount: Int,
    val category: CharCategory,
    val date: Long // Add a timestamp for the date
) : Serializable
