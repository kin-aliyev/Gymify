package com.example.gymify.home.presentation.workout_session_screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.home.presentation.components.BigActionButton
import com.example.gymify.home.presentation.components.CustomAlertDialog
import com.example.gymify.home.presentation.workout_session_screen.components.CustomTimer
import com.example.gymify.home.presentation.workout_session_screen.components.MainExerciseView
import com.example.gymify.home.presentation.workout_session_screen.components.RemainingExerciseTab
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSessionScreen(
    modifier: Modifier = Modifier,
    onAction: (WorkoutSessionAction) -> Unit,
    state: WorkoutSessionState,
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var showFinishDialog by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state.shouldFinishWorkout) {
        if (state.shouldFinishWorkout) {
            onAction(WorkoutSessionAction.FinishWorkout(state.timeElapsedSeconds))
        }
    }

    BackHandler {
        if (state.isActive) {
            showExitDialog = true
        } else onAction(WorkoutSessionAction.NavigateBack)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            BackTopBar(
                title = state.displayName,
                scrollBehavior = scrollBehavior,
                onBackIconClick = { if (state.isActive) showExitDialog = true else onAction(WorkoutSessionAction.NavigateBack) },
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 108.dp), // отступ под кнопку
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    CustomTimer(timeElapsedSeconds = state.timeElapsedSeconds)
                }

                item {
                    Crossfade(
                        targetState = state.mainSelectedExercise,
                        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                    ) {
                        it?.let {
                            MainExerciseView(
                                workoutExerciseWithExerciseInfo = it,
                                exerciseStats = state.mainSelectedExerciseStats,
                                userWeightUnit = state.userWeightUnit,
                                onExerciseCompleted = { onAction(WorkoutSessionAction.MarkMainExerciseCompleted) },
                                isWorkoutActive = state.isActive
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                item {
                    HorizontalDivider(
                        color = Color(0xFF3B4051),
                        thickness = 3.dp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 14.dp)
                    )
                }

                items(
                    state.selectedExercises,
                    key = { it.exercise.id }) { workoutExerciseWithExercise ->
                    val index = state.selectedExercises.indexOf(workoutExerciseWithExercise)
                    RemainingExerciseTab(
                        workoutExerciseWithExerciseInfo = workoutExerciseWithExercise,
                        userWeightUnit = state.userWeightUnit,
                        isWorkoutActive = state.isActive,
                        markRemainingExerciseCompleted = {
                            onAction(WorkoutSessionAction.MarkRemainingExerciseCompleted(it))
                        }
                    )

                    if (index != state.selectedExercises.lastIndex) {
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }

            BigActionButton(
                actionTitle = if (state.isActive) "Finish" else "Start",
                onButtonClick = {
                    if (state.isActive && state.selectedExercises.isNotEmpty()) showFinishDialog =
                        true
                    else if (state.isActive) onAction(WorkoutSessionAction.FinishWorkout(state.timeElapsedSeconds))
                    else onAction(WorkoutSessionAction.StartWorkout)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 30.dp, vertical = 20.dp)

            )

            if (showFinishDialog) {
                CustomAlertDialog(
                    onConfirmation = {
                        showFinishDialog = false
                        onAction(WorkoutSessionAction.FinishWorkout(state.timeElapsedSeconds))
                    },
                    onDismissRequest = { showFinishDialog = false },
                    warnText = "Not all exercises are completed",
                    title = "Finish Workout?"
                )
            }

            if (showExitDialog) {
                CustomAlertDialog(
                    onConfirmation = {
                        showExitDialog = false
                        onAction(WorkoutSessionAction.NavigateBack)
                    },
                    onDismissRequest = { showExitDialog = false },
                    warnText = "Your progress will be lost",
                    title = "Exit Workout?"
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutSessionScreenPreview() {
    GymifyTheme {
        WorkoutSessionScreen(
            onAction = { },
            state = WorkoutSessionState(
                timeElapsedSeconds = 2050,
                displayName = "Daily Exercises",
                mainSelectedExerciseStats = ExerciseStats(
                    maxWeight = 70f,
                    maxWeightReps = 8,
                    lastWeight = 65f,
                    lastWeightReps = 10,
                    exerciseId = 0
                ),
                mainSelectedExercise = WorkoutExerciseWithExerciseInfo(
                    workoutExercise = WorkoutExercise(
                        id = 0,
                        workoutPlanId = 0,
                        exerciseId = 0,
                        reps = 10,
                        sets = 3,
                        weight = 100f
                    ),
                    exercise = Exercise(
                        id = 0,
                        exerciseNameId = "back_lat_pulldown_wide_grip",
                        firstIconId = "ic_back_pullup_end", //ic_chest_barbell_bench_press
                        iconSecondary = "ic_back_pullup_end",
                        supportsWeight = true,
                        muscleGroup = MuscleGroup.ABDOMINALS
                    )
                ),
                selectedExercises = listOf(
                    WorkoutExerciseWithExerciseInfo(
                        workoutExercise = WorkoutExercise(
                            id = 0,
                            workoutPlanId = 0,
                            exerciseId = 0,
                            reps = 10,
                            sets = 3,
                            weight = 100f
                        ),
                        exercise = Exercise(
                            id = 0,
                            exerciseNameId = "exercise_back_pullup",
                            firstIconId = "ic_back_pullup_start",
                            iconSecondary = "ic_back_pullup_end",
                            supportsWeight = true,
                            muscleGroup = MuscleGroup.ABDOMINALS
                        )
                    ),
                    WorkoutExerciseWithExerciseInfo(
                        workoutExercise = WorkoutExercise(
                            id = 0,
                            workoutPlanId = 0,
                            exerciseId = 0,
                            reps = 10,
                            sets = 3,
                            weight = 100f
                        ),
                        exercise = Exercise(
                            id = 0,
                            exerciseNameId = "exercise_back_pullup",
                            firstIconId = "ic_back_pullup_start",
                            iconSecondary = "ic_back_pullup_end",
                            supportsWeight = true,
                            muscleGroup = MuscleGroup.ABDOMINALS
                        )
                    ),
                )
            )
        )
    }
}