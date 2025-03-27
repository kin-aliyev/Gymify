@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymify.main.presentation.analytics_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.main.presentation.analytics_screen.components.BmiCard
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.core.presentation.navigation.analytics.Analytics
import com.example.gymify.main.presentation.components.IconTopBar
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    bmiValue: Float,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier,
        topBar = {
            IconTopBar(
                title = "Workout History",
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigate = { onNavigate(it) },
                selectedDestination = selectedDestination,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
        }


    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Spacer(Modifier.height(10.dp))

            BmiCard(bmiValue = bmiValue, modifier = Modifier.padding(horizontal = 12.dp))
        }
    }
}

@Preview
@Composable
private fun AnalyticsScreenPreview() {
    GymifyTheme {
        AnalyticsScreen(bmiValue = 23.9f, onNavigate = { }, selectedDestination = Analytics)
    }

}