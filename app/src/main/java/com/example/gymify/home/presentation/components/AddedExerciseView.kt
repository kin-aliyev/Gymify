package com.example.gymify.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.rounded.Clear
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.home.util.ExerciseIconMapper
import com.example.gymify.home.util.ExerciseNameMapper
import com.example.gymify.core.util.formatWeight
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.model.WorkoutExercise
//import com.example.gymify.home.util.ExerciseIconMapper
//import com.example.gymify.home.util.ExerciseNameMapper
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay

@Composable
fun AddedExerciseView(
    modifier: Modifier = Modifier,
    workoutExerciseWithExerciseInfo: WorkoutExerciseWithExerciseInfo,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    userWeightUnit: UserWeightUnit,
    isForMakeWorkoutPlan: Boolean = true
) {
    val firstIcon = workoutExerciseWithExerciseInfo.exercise.firstIconId
    val secondIcon = workoutExerciseWithExerciseInfo.exercise.iconSecondary
    val weightUnit = if (userWeightUnit.unitId == 0) "lbs" else "kg"

    var currentIcon by remember { mutableStateOf(firstIcon) }

    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(
        key1 = workoutExerciseWithExerciseInfo.exercise.firstIconId,
        key2 = workoutExerciseWithExerciseInfo.exercise.iconSecondary
    ) {
        if (workoutExerciseWithExerciseInfo.exercise.iconSecondary != null) {
            while (true) {
                delay(1500)
                currentIcon = if (currentIcon == firstIcon) secondIcon else firstIcon
            }
        }
    }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .height(72.dp)
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .height(72.dp)
                .weight(1f) 
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(ExerciseIconMapper.getIcon(currentIcon)),
                contentDescription = null,
                modifier = Modifier.size(62.dp)
            )
        }

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .height(72.dp)
                .weight(2.8f)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(
                            ExerciseNameMapper.getName(
                                workoutExerciseWithExerciseInfo.exercise.exerciseNameId
                            )
                        ),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontFamily = rubikFontFamily
                    )

                    Spacer(Modifier.height(4.dp))


                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${workoutExerciseWithExerciseInfo.workoutExercise.sets}x${workoutExerciseWithExerciseInfo.workoutExercise.reps}",
                            fontSize = 13.sp,
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Light,
                            style = TextStyle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        Color(0xFF617BB6)
                                    )
                                )
                            ),
                            modifier = Modifier.drawBehind {
                                val underlineY = size.height
                                drawLine(
                                    color = Color(0xFF4666B1),
                                    start = Offset(x = 0f, y = underlineY),
                                    end = Offset(x = size.width, y = underlineY),
                                    strokeWidth = 2.5f
                                )
                            }
                        )

                        if (workoutExerciseWithExerciseInfo.exercise.supportsWeight) {
                            Text(
                                text = "(${
                                    formatWeight(
                                        workoutExerciseWithExerciseInfo.workoutExercise.weight.toString(),
                                        userWeightUnit
                                    )
                                }$weightUnit)",
                                fontSize = 13.sp,
                                fontFamily = rubikFontFamily,
                                fontWeight = FontWeight.Light,
                                style = TextStyle(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.primary,
                                            Color(0xFF617BB6)
                                        )
                                    )
                                ),
                            )
                        }

                    }
                }

                Spacer(Modifier.weight(1f))

                if (isForMakeWorkoutPlan) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Create,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable(
                                    onClick = { onEditClick(workoutExerciseWithExerciseInfo.workoutExercise.workoutPlanId) },
                                    role = Role.Button
                                )
                        )

                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable(
                                    onClick = { showDeleteDialog = true },
                                    role = Role.Button
                                )
                        )
                    }
                }
            }
        }
        if (showDeleteDialog) {
            CustomAlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                onConfirmation = {
                    showDeleteDialog = false
                    onDeleteClick(workoutExerciseWithExerciseInfo.workoutExercise.id)
                },
                title = "Remove this exercise?",
                warnText = "It will no longer appear in this workout"
            )
        }
    }
}

@Preview
@Composable
private fun AddedExerciseViewPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            AddedExerciseView(
                onEditClick = {},
                onDeleteClick = {},
                userWeightUnit = UserWeightUnit.KG,
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
                        exerciseNameId = "exercise_back_pullup",
                        firstIconId = "ic_back_pullup_start",
                        iconSecondary = "ic_back_pullup_end",
                        supportsWeight = true,
                        muscleGroup = MuscleGroup.ABDOMINALS
                    )
                )
            )
        }
    }
}