package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.WorkoutExerciseEntity
import com.example.gymify.main.domain.model.WorkoutExercise

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