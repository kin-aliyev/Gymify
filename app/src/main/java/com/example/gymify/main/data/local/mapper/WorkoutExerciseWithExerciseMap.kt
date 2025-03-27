package com.example.gymify.main.data.local.mapper

import com.example.gymify.main.data.local.entity.relations.WorkoutExerciseWithExercise
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo

fun WorkoutExerciseWithExercise.toDomain(): WorkoutExerciseWithExerciseInfo {
    return WorkoutExerciseWithExerciseInfo(
        workoutExercise = this.workoutExercise.toDomain(),
        exercise = this.exercise.toDomain()
    )
}

