package com.example.gymify.home.presentation.user_workouts_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun WorkoutCardList(
    modifier: Modifier = Modifier,
    workoutPlans: List<WorkoutPlan>,
    onWorkoutPlanClick: (Int) -> Unit,
    onAddWorkoutPlanClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(28.dp)
            )
            .padding(horizontal = 16.dp, vertical = 18.dp)
            .padding(top = 6.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(workoutPlans) { workoutPlan ->
                WorkoutCard(
                    workoutPlan = workoutPlan,
                    onWorkoutPlanClick = { onWorkoutPlanClick(workoutPlan.id) }
                )
            }

            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(vertical = 5.dp, horizontal = 14.dp)
                        .clickable(onClick = onAddWorkoutPlanClick, role = Role.Button)

                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(-10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

//                        Icon(
//                            imageVector = Icons.Rounded.Add,
//                            contentDescription = null,
//                            tint = Color(0xFFEBEBEB),
//                            modifier = Modifier
//                                .size(40.dp)
//
//                        )


                        Text(
                            text = "Make New Workout",
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFEBEBEB),
                            fontSize = 11.sp
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutCardListPreview() {
    GymifyTheme {
        WorkoutCardList(
            onWorkoutPlanClick = {},
            onAddWorkoutPlanClick = {},
            workoutPlans = listOf(
                WorkoutPlan(
                    workoutPlanName = "Full-Body HIIT",
                    lastUsedDate = 1720436400000L
                ),
                WorkoutPlan(
                    workoutPlanName = "Full-Body HIIT",
                    lastUsedDate = 1720436400000L
                )
            )
        )
    }

}