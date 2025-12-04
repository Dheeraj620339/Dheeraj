package com.example.targetfocus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.targetfocus.ui.components.PlannerTopAppBar
import com.example.targetfocus.ui.components.TabSection
import com.example.targetfocus.ui.components.TaskList
import com.example.targetfocus.viewmodel.PlannerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyPlannerScreen(
    viewModel: PlannerViewModel
) {
    Scaffold(
        topBar = {
            PlannerTopAppBar(
                onMenuItemClick = { menuItem ->
                    // Handle menu clicks (placeholder for now)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Handle FAB click (placeholder for now)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabSection(
                selectedTab = viewModel.selectedTab,
                onTabSelected = { index ->
                    viewModel.selectTab(index)
                }
            )

            val tasks = if (viewModel.selectedTab == 0) {
                viewModel.todayTasks
            } else {
                viewModel.tomorrowTasks
            }

            TaskList(
                tasks = tasks,
                onTaskCheckedChange = { taskId, _ ->
                    viewModel.toggleTaskCompletion(taskId, viewModel.selectedTab == 0)
                }
            )
        }
    }
}
