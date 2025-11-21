@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymify.analytics.presentation.analytics_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.analytics.presentation.analytics_screen.components.BmiCard
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.analytics.navigation.Analytics
import com.example.gymify.home.domain.model.TimeScale
import com.example.gymify.analytics.presentation.analytics_screen.components.BarData
import com.example.gymify.analytics.presentation.analytics_screen.components.WorkoutActivityChart
import com.example.gymify.home.presentation.components.IconTopBar
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
    state: AnalyticsState,
    onAction: (AnalyticsAction) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
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
                    .padding(bottom = 12.dp, top = 6.dp)
            )
        }


    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Spacer(Modifier.height(10.dp))

            WorkoutActivityChart(
                barDataList = state.data,
                selectedIndex = state.selectedIndex,
                selectedTimeScale = state.selectedScale,
                isBarItemSelected = state.selectedIndex != null,
                onChangeTimeScale = { onAction(AnalyticsAction.ChangeTimeScale(it)) },
                onBarClick = { onAction(AnalyticsAction.OnBarClick(it)) },
                selectedDateTitle = state.selectedDateTitle,
                selectedBarMinutes = state.selectedBarDurationMinutes,
                modifier = Modifier.padding(horizontal = 12.dp),
                totalTimeMinutes = state.totalTimeMinutes
            )


            Spacer(Modifier.height(16.dp))


            BmiCard(bmiValue = state.bmiValue, modifier = Modifier.padding(horizontal = 12.dp))


        }
    }
}

@Preview
@Composable
private fun AnalyticsScreenPreview() {
    GymifyTheme(darkTheme = true) {
        AnalyticsScreen(
            onNavigate = { },
            selectedDestination = Analytics,
            state = AnalyticsState(
                data = listOf(
                    BarData(90, "Mon", null),
                    BarData(30, "Tue", null),
                    BarData(80, "Wed", null),
                    BarData(20, "Thu", null),
                    BarData(120, "Fri", null),
                    BarData(40, "Sat", null),
                    BarData(70, "Sun", null),
                ),
                selectedScale = TimeScale.DAY,
                selectedIndex = 4,
                selectedBarDurationMinutes = 120,
                selectedDateTitle = "21 November",
                totalTimeMinutes = 450,
                bmiValue = 24.5f,
            ),
            onAction = { }
        )
    }
}