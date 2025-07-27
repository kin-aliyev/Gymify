package com.example.gymify.home.presentation.workout_plan_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.R
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.home.presentation.components.AddedExerciseView
import com.example.gymify.home.presentation.components.BigActionButton
import com.example.gymify.home.presentation.workout_plan_detail_screen.components.WorkoutPlanBanner
import com.example.gymify.home.presentation.workout_plan_detail_screen.components.WorkoutPlanNameAndDuration
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun WorkoutPlanDetailScreen(
    modifier: Modifier = Modifier,
    state: WorkoutPlanDetailState,
    onAction: (WorkoutPlanDetailAction) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 110.dp)
        ) {
            WorkoutPlanBanner(
                imagePath = state.imagePath,
                workoutIconId = state.displayIconId ?: R.drawable.icon_banner_workout_plan,
                isWorkoutInDatabase = state.isWorkoutInDatabase,
                onDeleteWorkoutClick = {
                    state.workoutId?.let {
                        onAction(
                            WorkoutPlanDetailAction.OnDeleteWorkoutClick(it)
                        )
                    }
                },
                onEditWorkoutClick = {
                    state.workoutId?.let {
                        onAction(
                            WorkoutPlanDetailAction.OnEditWorkoutClick(it)
                        )
                    }
                },
                onNavigateBackClick = { onAction(WorkoutPlanDetailAction.OnNavigateBackClick) }
            )

            Spacer(Modifier.height(14.dp))

            WorkoutPlanNameAndDuration(
                estimatedTimeMinutes = state.workoutEstimatedTimeMinutes,
                workoutPlanDisplayName = state.displayName,
                modifier = Modifier
                    .padding(horizontal = 17.dp)
            )

            Spacer(Modifier.height(12.dp))


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.selectedExercises) { workoutExerciseWithExerise ->
                    AddedExerciseView(
                        workoutExerciseWithExerciseInfo = workoutExerciseWithExerise,
                        onEditClick = { onAction(WorkoutPlanDetailAction.OnEditWorkoutClick(it)) },
                        onDeleteClick = { onAction(WorkoutPlanDetailAction.DeleteWorkoutExercise(it)) },
                        userWeightUnit = state.userWeightUnit
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

        }
        BigActionButton(
            actionTitle = "Start",
            onButtonClick = {
                if (state.isWorkoutInDatabase) state.workoutId?.let {
                    onAction(WorkoutPlanDetailAction.OnStartClick.WithWorkoutId(it))
                } else {
                    state.workoutPlanNameId?.let {
                        onAction(WorkoutPlanDetailAction.OnStartClick.WithWorkoutNameId(it))
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp, vertical = 20.dp)
        )
    }
}


@Preview
@Composable
private fun WorkoutPlanDetailScreenPreview() {
    GymifyTheme {
        WorkoutPlanDetailScreen(
            onAction = {},
            state = WorkoutPlanDetailState(
                displayName = "Daily Exercises",
                workoutEstimatedTimeMinutes = 48,
                workoutPlanIconId = "full_body",
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
                    ), WorkoutExerciseWithExerciseInfo(
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
                            supportsWeight = true,
                            muscleGroup = MuscleGroup.ABDOMINALS
                        )
                    )
                )
            )
        )
    }
}