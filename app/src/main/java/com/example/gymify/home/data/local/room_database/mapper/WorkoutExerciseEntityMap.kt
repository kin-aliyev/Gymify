package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.WorkoutExerciseEntity
import com.example.gymify.home.domain.model.WorkoutExercise

fun WorkoutExerciseEntity.toDomain(): WorkoutExercise {
    return WorkoutExercise(
        id = this.id,
        workoutPlanId = this.workoutPlanId,
        exerciseId = this.exerciseId,
        reps = this.reps,
        sets = this.sets,
        weight = this.weight
    )
}

fun WorkoutExercise.toEntity(): WorkoutExerciseEntity {
    return WorkoutExerciseEntity(
        id = this.id,
        workoutPlanId = this.workoutPlanId,
        exerciseId = this.exerciseId,
        reps = this.reps,
        sets = this.sets,
        weight = this.weight
    )
}