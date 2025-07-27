package com.example.gymify.home.presentation.home_screen

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
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.core.presentation.navigation.home.Home
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.presentation.components.IconTopBar
import com.example.gymify.home.presentation.home_screen.components.YourWorkoutPlans
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
    onNavigateToAllUserWorkoutPlans: () -> Unit,
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            IconTopBar(
                title = "Home",
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

            YourWorkoutPlans(
                onAddNewWorkoutClick = { onAction(HomeAction.OnAddNewWorkoutClick) },
                userWorkoutPlans = state.userWorkoutPlans,
                modifier = Modifier.padding(horizontal = 12.dp),
                onWorkoutPlanClick = { onAction(HomeAction.OnWorkoutPlanClick(it))},
                onNavigateToAllUserWorkoutPlans = onNavigateToAllUserWorkoutPlans
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    GymifyTheme {
        HomeScreen(
            onNavigate = { },
            selectedDestination = Home,
            onNavigateToAllUserWorkoutPlans = {},
            onAction = {},
            state = HomeState(
                userWorkoutPlans = listOf(
                    WorkoutPlan(

                    ),
                    WorkoutPlan(

                    )
                )
            )
        )
    }
}