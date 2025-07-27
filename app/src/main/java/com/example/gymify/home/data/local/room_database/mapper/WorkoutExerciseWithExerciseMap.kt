package com.example.gymify.home.data.local.room_database.mapper

import com.example.gymify.home.data.local.room_database.entity.relations.WorkoutExerciseWithExercise
import com.example.gymify.home.domain.model.WorkoutExerciseWithExerciseInfo

fun WorkoutExerciseWithExercise.toDomain(): WorkoutExerciseWithExerciseInfo {
    return WorkoutExerciseWithExerciseInfo(
        workoutExercise = this.workoutExercise.toDomain(),
        exercise = this.exercise.toDomain()
    )
}

