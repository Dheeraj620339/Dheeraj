package com.example.targetfocus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.targetfocus.ui.theme.DeepBlue

@Composable
fun TabSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("TODAY", "TOMORROW")

    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = DeepBlue,
        indicator = { tabPositions ->
            if (selectedTab < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = DeepBlue,
                    height = 3.dp
                )
            }
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedTab == index) DeepBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}
