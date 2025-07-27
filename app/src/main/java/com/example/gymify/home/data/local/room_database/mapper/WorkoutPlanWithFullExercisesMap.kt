package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.relations.WorkoutPlanWithFullExercises
import com.example.gymify.home.domain.model.WorkoutPlanWithFullExercisesInfo

fun WorkoutPlanWithFullExercises.toDomain(): WorkoutPlanWithFullExercisesInfo {
    return WorkoutPlanWithFullExercisesInfo(
        workoutPlan = this.workoutPlan.toDomain(),
        exercises = this.workoutExercisesWithExercises.map { it.toDomain() }
    )
}