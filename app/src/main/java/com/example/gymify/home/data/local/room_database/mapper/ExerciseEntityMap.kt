package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.ExerciseEntity
import com.example.gymify.home.domain.model.Exercise

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