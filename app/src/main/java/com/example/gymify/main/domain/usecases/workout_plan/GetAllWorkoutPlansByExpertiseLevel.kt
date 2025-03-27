package com.example.gymify.main.domain.usecases.workout_plan

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.main.domain.model.WorkoutPlan
import com.example.gymify.main.domain.repository.WorkoutPlanRepository

class GetAllWorkoutPlansByExpertiseLevel(
    private val repository: WorkoutPlanRepository
) {
    suspend operator fun invoke(expertiseLevel: ExpertiseLevel): List<WorkoutPlan> {
        return repository.getAllWorkoutPlansByExpertiseLevel(expertiseLevel)
    }
}