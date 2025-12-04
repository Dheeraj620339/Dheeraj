package com.example.targetfocus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.targetfocus.data.Task

class PlannerViewModel : ViewModel() {
    var selectedTab by mutableStateOf(0)
        private set

    var todayTasks by mutableStateOf(getTodaySampleTasks())
        private set

    var tomorrowTasks by mutableStateOf(getTomorrowSampleTasks())
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun toggleTaskCompletion(taskId: Int, isToday: Boolean) {
        if (isToday) {
            todayTasks = todayTasks.map {
                if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
                else it
            }
        } else {
            tomorrowTasks = tomorrowTasks.map {
                if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
                else it
            }
        }
    }

    private fun getTodaySampleTasks() = listOf(
        Task(1, "5:00 AM – 8:00 AM", "Study"),
        Task(2, "8:00 AM – 9:00 AM", "Breakfast"),
        Task(3, "9:00 AM – 1:00 PM", "Work"),
        Task(4, "1:00 PM – 2:00 PM", "Lunch Break"),
        Task(5, "2:00 PM – 6:00 PM", "Project Development"),
        Task(6, "6:00 PM – 7:00 PM", "Exercise"),
        Task(7, "7:00 PM – 8:00 PM", "Dinner"),
        Task(8, "8:00 PM – 10:00 PM", "Reading")
    )

    private fun getTomorrowSampleTasks() = listOf(
        Task(9, "6:00 AM – 7:00 AM", "Morning Jog"),
        Task(10, "7:00 AM – 8:00 AM", "Breakfast"),
        Task(11, "9:00 AM – 12:00 PM", "Team Meeting"),
        Task(12, "12:00 PM – 1:00 PM", "Lunch"),
        Task(13, "1:00 PM – 5:00 PM", "Code Review"),
        Task(14, "5:00 PM – 6:00 PM", "Planning Session"),
        Task(15, "7:00 PM – 8:00 PM", "Dinner"),
        Task(16, "8:00 PM – 9:00 PM", "Meditation")
    )
}
