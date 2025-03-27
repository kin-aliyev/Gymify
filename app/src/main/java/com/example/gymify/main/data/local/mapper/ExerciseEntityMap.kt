package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.ExerciseEntity
import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.model.MuscleGroup

fun ExerciseEntity.toDomain(): Exercise {
    return Exercise(
        id = this.id,
        exerciseNameId = this.exerciseNameId,
        muscleGroup = this.muscleGroup,
        firstIconId = this.firstIconId,
        iconSecondary = this.secondIconId,
        supportsWeight = this.supportsWeight
    )
}

fun Exercise.toEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = this.id,
        exerciseNameId = this.exerciseNameId,
        muscleGroup = this.muscleGroup,
        firstIconId = this.firstIconId,
        secondIconId = this.iconSecondary,
        supportsWeight = this.supportsWeight
    )
}