package com.example.gymify.home.presentation.workout_session_screen.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.util.formatWeight
import com.example.gymify.home.domain.model.WorkoutExercise
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ExerciseProgressTab(
    modifier: Modifier = Modifier,
    workoutExercise: WorkoutExercise,
    userWeightUnit: UserWeightUnit,
    onExerciseCompleted: () -> Unit,
    isWorkoutActive: Boolean,
) {
    val coroutineScope = rememberCoroutineScope()
    val weightUnit = if (userWeightUnit.unitId == 0) "lbs" else "kg"

    val setCount = workoutExercise.sets ?: 0

    // Список состояний чек-боксов - пересоздаются только при смене ID упражнения
    val checkedStates = remember(workoutExercise.id) { mutableStateListOf<Boolean>() }

    val visibleStates = remember(workoutExercise.id) { mutableStateListOf<Boolean>() }

    val allChecked by remember(workoutExercise.id) {
        derivedStateOf {
            checkedStates.isNotEmpty() && checkedStates.all { it }
        }
    }

    // Явный сброс состояний при смене упражнения
    LaunchedEffect(workoutExercise.id) {
        checkedStates.clear()
        visibleStates.clear()
        repeat(setCount) {
            checkedStates.add(false)
            visibleStates.add(true)
        }
    }

    // Колбек на все выполненные упражнения
    LaunchedEffect(workoutExercise.id, allChecked) {
        if (allChecked && checkedStates.isNotEmpty()) {
            delay(300L)
            onExerciseCompleted()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        val contentModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp)

        if (setCount > 0 && checkedStates.size == setCount && visibleStates.size == setCount) {
            if (setCount > 2) {
                LazyColumn(
                    modifier = contentModifier
                        .heightIn(max = 130.dp)
                        .animateContentSize(),
                ) {
                    items(setCount) { setIndex ->
                        AnimatedVisibility(
                            visible = visibleStates[setIndex],
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                            ) {
                                SetRow(
                                    setIndex = setIndex,
                                    weight = workoutExercise.weight,
                                    reps = workoutExercise.reps,
                                    weightUnit = weightUnit,
                                    userWeightUnit = userWeightUnit,
                                    isChecked = checkedStates[setIndex],
                                    onCheckedChange = { checkStatus ->
                                        checkedStates[setIndex] = checkStatus
                                        if (checkStatus) {
                                            coroutineScope.launch {
                                                delay(500L) // задержка перед исчезновением
                                                visibleStates[setIndex] = false
                                            }
                                        }
                                    },
                                    isWorkoutActive = isWorkoutActive
                                )

                                if (setIndex != setCount - 1) {
                                    HorizontalDivider(
                                        color = Color(0xFF3B4051),
                                        thickness = 1.dp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = contentModifier,
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    repeat(setCount) { setIndex ->
                        SetRow(
                            setIndex = setIndex,
                            weight = workoutExercise.weight,
                            reps = workoutExercise.reps,
                            weightUnit = weightUnit,
                            userWeightUnit = userWeightUnit,
                            isChecked = checkedStates[setIndex],
                            onCheckedChange = { checkStatus ->
                                checkedStates[setIndex] = checkStatus
                                if (checkStatus) visibleStates[setIndex] = false
                            },
                            isWorkoutActive = isWorkoutActive
                        )

                        if (setIndex != setCount - 1) {
                            HorizontalDivider(
                                color = Color(0xFF3B4051),
                                thickness = 1.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SetRow(
    setIndex: Int,
    weight: Float?,
    reps: Int?,
    weightUnit: String,
    userWeightUnit: UserWeightUnit,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isWorkoutActive: Boolean,
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Set ${setIndex + 1}:",
                fontFamily = rubikFontFamily,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = if (weight != null) formatWeight(
                    weight.toString(), userWeightUnit
                ) + "${weightUnit} x ${reps} Reps" else "${reps} reps",
                fontFamily = rubikFontFamily,
                color = Color(0xFFEBEBEB),
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            )
        }

        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                if (isWorkoutActive) {
                    onCheckedChange(it)
                } else {
                    Toast.makeText(
                        context,
                        "Start your workout first.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.primary,
                checkmarkColor = MaterialTheme.colorScheme.background,
            ),
        )
    }
}


@Preview
@Composable
private fun ExerciseProgressTabPreview() {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            ExerciseProgressTab(
                userWeightUnit = UserWeightUnit.KG,
                onExerciseCompleted = { },
                isWorkoutActive = false,
                workoutExercise = WorkoutExercise(
                    id = 0,
                    workoutPlanId = 0,
                    exerciseId = 0,
                    reps = 6,
                    sets = 3,
                    weight = 50f
                )
            )
        }
    }
}