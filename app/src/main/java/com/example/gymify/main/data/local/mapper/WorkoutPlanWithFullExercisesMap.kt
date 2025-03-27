package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.relations.WorkoutPlanWithFullExercises
import com.example.gymify.main.domain.model.WorkoutPlanWithFullExercisesInfo

fun WorkoutPlanWithFullExercises.toDomain(): WorkoutPlanWithFullExercisesInfo {
    return WorkoutPlanWithFullExercisesInfo(
        workoutPlan = this.workoutPlan.toDomain(),
        exercises = this.workoutExercisesWithExercises.map { it.toDomain() }
    )
}