package com.example.gymify.home.presentation.workout_session_screen.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RemainingExerciseTab(
    modifier: Modifier = Modifier,
    workoutExerciseWithExerciseInfo: WorkoutExerciseWithExerciseInfo,
    userWeightUnit: UserWeightUnit,
    markRemainingExerciseCompleted: (Int) -> Unit,
    isWorkoutActive: Boolean = false,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val weightUnit = if (userWeightUnit.unitId == 0) "lbs" else "kg"

    var checkedStatus by remember(workoutExerciseWithExerciseInfo.workoutExercise.id) {
        mutableStateOf(false)
    }
    var visibility by remember(workoutExerciseWithExerciseInfo.workoutExercise.id) {
        mutableStateOf(true)
    }

    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(600, easing = FastOutSlowInEasing)) +
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(600, easing = FastOutSlowInEasing)
                ),
        exit = fadeOut(animationSpec = tween(600, easing = FastOutSlowInEasing)) +
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(600, easing = FastOutSlowInEasing)
                )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(65.dp)
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Image(
                    painter = painterResource(
                        ExerciseIconMapper.getIcon(
                            workoutExerciseWithExerciseInfo.exercise.firstIconId
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(Modifier.width(10.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(65.dp)
                    .weight(2.7f)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
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

                        Spacer(Modifier.height(1.5.dp))


                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "${workoutExerciseWithExerciseInfo.workoutExercise.sets}x${workoutExerciseWithExerciseInfo.workoutExercise.reps}",
                                fontSize = 12.sp,
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

                    Checkbox(
                        checked = checkedStatus,
                        onCheckedChange = { checkStatus ->
                            if (isWorkoutActive) {
                                checkedStatus = checkStatus
                                if (checkStatus) {
                                    coroutineScope.launch {
                                        visibility = false
                                        delay(600L) // задержка перед исчезновением
                                        markRemainingExerciseCompleted(workoutExerciseWithExerciseInfo.exercise.id)
                                    }
                                }
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
        }
    }

}

@Preview
@Composable
private fun RemainingExerciseTabPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            RemainingExerciseTab(
                userWeightUnit = UserWeightUnit.KG,
                markRemainingExerciseCompleted = { },
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
                        firstIconId = "ic_chest_barbell_bench_press",
                        iconSecondary = "ic_back_pullup_end",
                        supportsWeight = true,
                        muscleGroup = MuscleGroup.ABDOMINALS
                    )
                )
            )
        }
    }

}