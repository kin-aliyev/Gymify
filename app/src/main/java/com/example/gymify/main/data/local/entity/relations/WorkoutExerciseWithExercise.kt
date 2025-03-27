package com.example.gymify.main.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.main.data.local.entity.ExerciseEntity
import com.example.gymify.main.data.local.entity.WorkoutExerciseEntity

data class WorkoutExerciseWithExercise(
    @Embedded val workoutExercise: WorkoutExerciseEntity,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id"
    )
    val exercise: ExerciseEntity
)