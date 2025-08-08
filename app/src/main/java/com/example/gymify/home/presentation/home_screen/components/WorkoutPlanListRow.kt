package com.example.gymify.home.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.data.local.predefined.PredefinedWorkoutExercises
import com.example.gymify.home.data.local.predefined.PredefinedWorkoutPlans

@Composable
fun WorkoutPlanListRow(
    modifier: Modifier = Modifier,
    workoutPlanKeys: List<String>,
    onWorkoutPlanClick: (String?) -> Unit,
    userExpertiseLevel: ExpertiseLevel,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        items(workoutPlanKeys) { key ->
            val workoutPlan = PredefinedWorkoutPlans.getWorkoutPlan(key)!!
            val workoutExercises = PredefinedWorkoutExercises.getExercisesForWorkoutPlan(key)
            val estimatedTimeMinutes = workoutExercises!!.sumOf { it.sets!! } * 4

            PredefinedWorkoutPlanListRowItem(
                workoutPlan = workoutPlan,
                onWorkoutPlanClick = onWorkoutPlanClick,
                estimatedTimeMinutes = estimatedTimeMinutes,
                userExpertiseLevel = userExpertiseLevel
            )
        }
    }
}