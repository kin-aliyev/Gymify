package com.example.gymify.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.gymify.core.util.ExerciseIconMapper
//import com.example.gymify.core.util.ExerciseNameMapper
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun AddedExerciseView(
    modifier: Modifier = Modifier,
    workoutExerciseWithExerciseInfo: WorkoutExerciseWithExerciseInfo
) {
    val exercise = workoutExerciseWithExerciseInfo.exercise

//    val iconResId = ExerciseIconMapper.getIcon(exercise.stringId)
//    val nameResId = ExerciseNameMapper.getName(exercise.nameKey)

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .aspectRatio(1.5f)
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
//            Icon(
//                painter = painterResource(iconResId),
//                contentDescription = null,
//
//                )
        }

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .weight(3f)
                .height(80.dp)
                .aspectRatio(3f)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(80.dp),
                    verticalArrangement = Arrangement.Center
                ) {
//                    Text(
//                        text = stringResource(nameResId),
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Normal,
//                        color = Color.White,
//                        fontFamily = rubikFontFamily
//                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "${workoutExerciseWithExerciseInfo.workoutExercise.sets}x${workoutExerciseWithExerciseInfo.workoutExercise.reps}",
                        fontSize = 13.sp,
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
                                color = Color(0xFF3B4051),
                                start = Offset(x = 0f, y = underlineY),
                                end = Offset(x = size.width, y = underlineY),
                                strokeWidth = 3f
                            )
                        }
                    )
                }

                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

            }
        }
    }
}

@Preview
@Composable
private fun AddedExerciseViewPreview() {
    GymifyTheme {
//        AddedExerciseView()
    }
}