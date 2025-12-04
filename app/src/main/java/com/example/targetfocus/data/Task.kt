package com.example.targetfocus.data

data class Task(
    val id: Int,
    val timeRange: String,
    val name: String,
    var isCompleted: Boolean = false
)
