package com.example.gymify.core.presentation.navigation.home

import com.example.gymify.core.presentation.navigation.BottomNavSection
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.home.domain.model.MuscleGroup
import kotlinx.serialization.Serializable

@Serializable
object Home : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
data class MakeWorkoutPlan(
    val exerciseId: Int? = null,
    val workoutPlanNameId: String? = null
) : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
object MuscleGroup : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
data class Exercises(val muscleGroup: MuscleGroup) : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
object UserWorkoutPlans : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
data class WorkoutPlanDetail(
    val workoutPlanId: Int? = null,
    val workoutPlanNameId: String? = null
) : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
data class WorkoutSession(
    val workoutPlanId: Int? = null,
    val workoutPlanNameId: String? = null
) : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}

@Serializable
data class WorkoutComplete(
    val timeElapsedSeconds: Int
) : NavigationDestination {
    override val section: BottomNavSection = BottomNavSection.HOME
}