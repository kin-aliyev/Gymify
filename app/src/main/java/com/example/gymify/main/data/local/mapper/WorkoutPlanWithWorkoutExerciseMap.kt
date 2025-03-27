package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.relations.helpers.WorkoutPlanWithWorkoutExercises
import com.example.gymify.main.domain.model.WorkoutPlanRelationWorkoutExercises

fun WorkoutPlanWithWorkoutExercises.toDomain(): WorkoutPlanRelationWorkoutExercises {
    return WorkoutPlanRelationWorkoutExercises(
        workoutPlan = this.workoutPlan.toDomain(),
        workoutExercises = this.workoutExercises.map { it.toDomain() }
    )
}