package com.example.gymify.home.domain.usecases.workout_plan

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.repository.WorkoutPlanRepository

class GetAllWorkoutPlansByExpertiseLevel(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(expertiseLevel: ExpertiseLevel): List<WorkoutPlan> {
        return repository.getAllWorkoutPlansByExpertiseLevel(expertiseLevel)
    }
}