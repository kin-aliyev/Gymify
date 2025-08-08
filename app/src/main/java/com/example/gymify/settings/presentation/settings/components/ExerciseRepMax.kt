package com.example.gymify.settings.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ExerciseRepMax(
    modifier: Modifier = Modifier,
    exerciseName: String,
    maxWeight: String,
    userWeightUnit: UserWeightUnit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(75.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        Color(0xFF617BB6)
                    )
                ),
                shape = CircleShape
            )
            .border(width = 4.dp, color = MaterialTheme.colorScheme.surface, shape = CircleShape)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-6).dp)
        ) {
            Text(
                text = exerciseName,
                fontSize = 11.sp,
                fontFamily = rubikFontFamily,
                color = Color(0XFFEBEBEB).copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium

            )

            Text(
                text = if (userWeightUnit.unitId == 0) "${maxWeight}lbs" else "${maxWeight}kg",
                fontSize = 13.sp,
                color = Color(0XFF3B4051),
                fontWeight = FontWeight.Bold,
                fontFamily = rubikFontFamily

            )
        }

    }


}

@Preview
@Composable
private fun ExerciseRepMaxPreview() {
    GymifyTheme {
        ExerciseRepMax(
            exerciseName = "Squat",
            maxWeight = "150",
            userWeightUnit = UserWeightUnit.KG
        )
    }
}