package com.example.gymify.home.presentation.home_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.data.local.predefined.WorkoutPlanKeys
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.util.WorkoutPlanIconMapper
import com.example.gymify.home.util.WorkoutPlanNameMapper
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun PredefinedWorkoutPlanListRowItem(
    modifier: Modifier = Modifier,
    workoutPlan: WorkoutPlan,
    onWorkoutPlanClick: (String?) -> Unit,
    estimatedTimeMinutes: Int,
    userExpertiseLevel: ExpertiseLevel,
) {
    val expertiseLevelString = when (userExpertiseLevel) {
        ExpertiseLevel.BEGINNER -> "Beginner"
        ExpertiseLevel.INTERMEDIATE -> "Intermediate"
        ExpertiseLevel.ADVANCED -> "Advanced"
    }

    Box(
        modifier = modifier
            .height(210.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(
                onClick = { onWorkoutPlanClick(workoutPlan.workoutPlanNameId) },
                role = Role.Button
            )
    ) {
        Image(
            painter = painterResource(WorkoutPlanIconMapper.getName(workoutPlan.iconId!!)),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(WorkoutPlanNameMapper.getName(workoutPlan.workoutPlanNameId!!)),
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFFAEC6FF),
                        )
                    )
                ),
            )

            Text(
                text = "${formatDuration(estimatedTimeMinutes)} | $expertiseLevelString",
                color = Color(0xFFEBEBEB),
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PredefinedWorkoutPlanListItemPreview() {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            PredefinedWorkoutPlanListRowItem(
                workoutPlan = WorkoutPlan(
                    id = 0,
                    workoutPlanNameId = WorkoutPlanKeys.PPL_PUSH_FIRST,
                    lastUsedDate = System.currentTimeMillis(),
                    iconId = WorkoutPlanKeys.PPL_PUSH_FIRST
                ),
                estimatedTimeMinutes = 32,
                onWorkoutPlanClick = {},
                userExpertiseLevel = ExpertiseLevel.BEGINNER
            )
        }
    }

}