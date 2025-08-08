package com.example.gymify.home.presentation.user_workouts_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.home.navigation.UserWorkoutPlans
import com.example.gymify.home.presentation.user_workouts_screen.components.WorkoutCardList
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserWorkoutsScreen(
    modifier: Modifier = Modifier,
    state: UserWorkoutsState,
    onAction: (UserWorkoutsAction) -> Unit,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            BackTopBar(
                title = "Your Workout Plans",
                scrollBehavior = scrollBehavior,
                onBackIconClick = { onAction(UserWorkoutsAction.NavigateBack) },
                modifier = Modifier.padding(horizontal = 10.dp)
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
        if (state.userWorkoutPlans.isNotEmpty()) {
            Log.d("HomeScreen", "User WorkoutsScreen User Plans: ${state.userWorkoutPlans}")
            WorkoutCardList(
                workoutPlans = state.userWorkoutPlans,
                onWorkoutPlanClick = { onAction(UserWorkoutsAction.OnWorkoutPlanClick(it)) },
                onAddWorkoutPlanClick = { onAction(UserWorkoutsAction.OnAddWorkoutClick) },
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding() + 10.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp
                    )
                    .padding(horizontal = 12.dp)
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_empty_folder),
                        contentDescription = null,
                        tint = Color(0xFFEBEBEB),
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "You haven't added workout plan yet",
                        fontSize = 14.sp,
                        color = Color(0xFFEBEBEB).copy(0.65f),
                        textAlign = TextAlign.Center,
                        fontFamily = rubikFontFamily,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(20.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                            .clickable(
                                onClick = { onAction(UserWorkoutsAction.OnAddWorkoutClick) },
                                role = Role.Button
                            )
                    ) {
                        Text(
                            text = "Make New Workout",
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEBEBEB),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserWorkoutsScreenPreview() {
    GymifyTheme {
        UserWorkoutsScreen(
            onAction = {},
            onNavigate = {},
            selectedDestination = UserWorkoutPlans,
            state = UserWorkoutsState(
                listOf(
//                    WorkoutPlan(
//                        workoutPlanName = "Full-Body HIIT",
//                        lastUsedDate = 1720436400000L
//                    ),
//                    WorkoutPlan(
//                        workoutPlanName = "Full-Body HIIT",
//                        lastUsedDate = 1720436400000L
//                    )
                )
            )
        )
    }

}