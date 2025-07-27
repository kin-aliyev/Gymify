package com.example.gymify.home.presentation.user_workouts_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.R
import com.example.gymify.home.util.TimeConverter
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.ui.theme.rubikFontFamily



@Composable
fun WorkoutCard(
    modifier: Modifier = Modifier,
    workoutPlan: WorkoutPlan,
    onWorkoutPlanClick: (Int) -> Unit
) {
    val formattedDate = workoutPlan.lastUsedDate?.let {
        TimeConverter().convertLongToDate(
            timeStamp = it,
            pattern = TimeConverter.Patterns.SHORT_DATE_TIME
        )
    }?.replaceFirstChar { it -> it.titlecase() } ?: "unknown date"


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .clickable(onClick = { onWorkoutPlanClick(workoutPlan.id) }, role = Role.Button)
    ) {
        AsyncImage(
            model = workoutPlan.iconUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            error = painterResource(R.drawable.icon_banner_workout_plan)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 14.dp, bottom = 20.dp)
        ) {
            Text(
                text = workoutPlan.workoutPlanName ?: "Unnamed Workout Plan",
                fontFamily = rubikFontFamily,
                color = Color(0xFFEBEBEB),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(R.string.last_used_date, formattedDate),
                fontFamily = rubikFontFamily,
                color = Color(0xFFEBEBEB),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
            )

        }
    }

}

@Preview
@Composable
private fun WorkoutCardPreview() {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            WorkoutCard(
                onWorkoutPlanClick = {},
                modifier = Modifier.padding(horizontal = 12.dp),
                workoutPlan = WorkoutPlan(
                    workoutPlanName = "Full-Body HIIT",
                    lastUsedDate = 1720436400000L
                )
            )
        }
    }
}