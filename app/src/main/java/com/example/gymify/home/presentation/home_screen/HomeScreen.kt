package com.example.gymify.home.presentation.home_screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.home.data.local.predefined.ProgramCategories
import com.example.gymify.home.navigation.Home
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.presentation.components.IconTopBar
import com.example.gymify.home.presentation.home_screen.components.WorkoutPlanListRow
import com.example.gymify.home.presentation.home_screen.components.YourWorkoutPlans
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
    onNavigateToAllUserWorkoutPlans: () -> Unit,
    state: HomeState,
    onAction: (HomeAction) -> Unit,
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
        LazyColumn(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding() + 8.dp,
                    top = paddingValues.calculateTopPadding()
                )
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            item {
                Spacer(Modifier.height(10.dp))
                Log.d("HomeScreen", "User Plans: ${state.userWorkoutPlans}")
                YourWorkoutPlans(
                    onAddNewWorkoutClick = { onAction(HomeAction.OnAddNewWorkoutClick) },
                    userWorkoutPlans = state.userWorkoutPlans,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    onWorkoutPlanClick = { onAction(HomeAction.OnWorkoutPlanClick(it)) },
                    onNavigateToAllUserWorkoutPlans = onNavigateToAllUserWorkoutPlans
                )

                Spacer(Modifier.height(28.dp))

            }

            item {

                Text(
                    text = "Upper-Lower Split",
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFEBEBEB),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 26.dp)
                )

                Spacer(Modifier.height(12.dp))

                WorkoutPlanListRow(
                    onWorkoutPlanClick = {
                        onAction(HomeAction.OnPredefinedWorkoutPlanClick(it!!))
                    },
                    userExpertiseLevel = state.userExpertiseLevel,
                    workoutPlanKeys = ProgramCategories.UpperLower
                )

                Spacer(Modifier.height(28.dp))
            }

            item {
                Text(
                    text = "Push Pull Legs",
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFEBEBEB),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 26.dp)
                )

                Spacer(Modifier.height(12.dp))

                WorkoutPlanListRow(
                    onWorkoutPlanClick = {
                        onAction(HomeAction.OnPredefinedWorkoutPlanClick(it!!))
                    },
                    userExpertiseLevel = state.userExpertiseLevel,
                    workoutPlanKeys = ProgramCategories.PushPullLegs
                )

            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
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