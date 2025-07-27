package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.relations.helpers.WorkoutPlanWithWorkoutExercises
import com.example.gymify.home.domain.model.WorkoutPlanRelationWorkoutExercises

fun WorkoutPlanWithWorkoutExercises.toDomain(): WorkoutPlanRelationWorkoutExercises {
    return WorkoutPlanRelationWorkoutExercises(
        workoutPlan = this.workoutPlan.toDomain(),
        workoutExercises = this.workoutExercises.map { it.toDomain() }
    )
}