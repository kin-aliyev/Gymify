package com.example.gymify.home.presentation.workout_session_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.util.formatWeight
import com.example.gymify.home.domain.model.ExerciseStats
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ExerciseRecordStats(
    modifier: Modifier = Modifier,
    exerciseStats: ExerciseStats?,
    userWeightUnit: UserWeightUnit
) {
    val weightUnit = if (userWeightUnit.unitId == 0) "lbs" else "kg"
    Box(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .height(84.dp)
            .border(
                width = 2.5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        Color(0XFF617BB6)
                    )
                ),
                shape = RoundedCornerShape(18.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Personal Records",
                fontSize = 18.sp,
                color = Color(0xFFEBEBEB),
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Medium
            )

            HorizontalDivider(
                color = Color(0xFF3B4051),
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            )


            if (exerciseStats == null) {
                Text(
                    text = "No lifts recorded yet",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = rubikFontFamily,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Row {
                        Text(
                            text = "Max: ",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "${formatWeight(exerciseStats.maxWeight.toString(), userWeightUnit)}x${exerciseStats.maxWeightReps} $weightUnit",
                            fontSize = 13.sp,
                            color = Color(0xFFEBEBEB),
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row {
                        Text(
                            text = "Latest: ",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "${formatWeight(exerciseStats.lastWeight.toString(), userWeightUnit)}x${exerciseStats.lastWeightReps} $weightUnit",
                            fontSize = 13.sp,
                            color = Color(0xFFEBEBEB),
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }

        }
    }
}

@Preview
@Composable
fun ExerciseRecordStatsPreview() {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            ExerciseRecordStats(
                userWeightUnit = UserWeightUnit.KG,
//                exerciseStats = ExerciseStats(
//                    maxWeight = 70f,
//                    maxWeightReps = 8,
//                    lastWeight = 65f,
//                    lastWeightReps = 10,
//                    exerciseId = 0
//                )
                exerciseStats = null
            )
        }
    }
}