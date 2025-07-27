package com.example.gymify.home.presentation.workout_plan_detail_screen.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun WorkoutPlanNameAndDuration(
    modifier: Modifier = Modifier,
    workoutPlanDisplayName: String,
    estimatedTimeMinutes: Int,
) {
    val dynamicFontSize = when (workoutPlanDisplayName.length) {
        in 0..4 -> 35.sp
        in 5..7 -> 33.sp
        in 8..13 -> 30.sp
        in 14..18 -> 27.sp
        in 19..21 -> 23.sp
        in 22..25 -> 21.sp
        else -> 18.sp
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = workoutPlanDisplayName,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = dynamicFontSize,
            color = Color(0xFFEBEBEB)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_clock),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(35.dp)
            )

            Text(
                text = formatDuration(estimatedTimeMinutes),
                color = Color(0xFFEBEBEB),
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )
        }
    }
}


private fun formatDuration(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60

    return when {
        hours > 0 && remainingMinutes > 0 -> "${hours}hr ${remainingMinutes}min"
        hours > 0 -> "$hours hr"
        else -> "$remainingMinutes min"
    }
}


@Preview
@Composable
private fun WorkoutPlanNameAndDurationPreview() {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            WorkoutPlanNameAndDuration(
                workoutPlanDisplayName = "Daily Exercises",
                estimatedTimeMinutes = 48
            )
        }
    }
}