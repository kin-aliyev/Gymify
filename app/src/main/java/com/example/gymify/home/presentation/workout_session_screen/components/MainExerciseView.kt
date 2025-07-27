package com.example.gymify.home.presentation.workout_session_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.home.util.ExerciseIconMapper
import com.example.gymify.home.util.ExerciseNameMapper
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay

@Composable
fun MainExerciseView(
    modifier: Modifier = Modifier,
    workoutExerciseWithExerciseInfo: WorkoutExerciseWithExerciseInfo,
    exerciseStats: ExerciseStats?,
    userWeightUnit: UserWeightUnit,
    onExerciseCompleted: () -> Unit,
    isWorkoutActive: Boolean
) {
    val exercise = workoutExerciseWithExerciseInfo.exercise
    var currentIcon by remember(exercise.id) { mutableStateOf(exercise.firstIconId) }

    // Запускаем эффект для переключения иконок каждую секунду
    LaunchedEffect(key1 = exercise.firstIconId, key2 = exercise.iconSecondary) {
        // Если вторая иконка null, нет необходимости менять изображение
        if (exercise.iconSecondary != null) {
            while (true) {
                delay(2500)
                currentIcon =
                    if (currentIcon == exercise.firstIconId) exercise.iconSecondary else exercise.firstIconId
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.form_ellipse_1),
                contentDescription = null,
                modifier = Modifier.size(240.dp),
                tint = MaterialTheme.colorScheme.surface
            )

            Image(
                painter = painterResource(ExerciseIconMapper.getIcon(currentIcon)),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }


        Text(
            text = stringResource( ExerciseNameMapper.getName(exercise.exerciseNameId)),
            color = Color(0xFFEBEBEB),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontFamily = rubikFontFamily,
            modifier = Modifier.offset(y = -2.dp)
        )

        Spacer(Modifier.height(10.dp))

        ExerciseProgressTab(
            workoutExercise = workoutExerciseWithExerciseInfo.workoutExercise,
            userWeightUnit = userWeightUnit,
            onExerciseCompleted = onExerciseCompleted,
            isWorkoutActive = isWorkoutActive
        )

        Spacer(Modifier.height(12.dp))

        ExerciseRecordStats(
            exerciseStats = exerciseStats,
            userWeightUnit = userWeightUnit
        )

    }
}

@Preview
@Composable
private fun MainExerciseViewPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            MainExerciseView(
                userWeightUnit = UserWeightUnit.KG,
                onExerciseCompleted = { },
                workoutExerciseWithExerciseInfo = WorkoutExerciseWithExerciseInfo(
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
                exerciseStats = ExerciseStats(
                    maxWeight = 70f,
                    maxWeightReps = 8,
                    lastWeight = 65f,
                    lastWeightReps = 10,
                    exerciseId = 0
                ),
                isWorkoutActive = false
            )
        }
    }
}