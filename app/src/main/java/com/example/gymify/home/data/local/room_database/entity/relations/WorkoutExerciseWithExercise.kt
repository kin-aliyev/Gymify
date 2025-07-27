package com.example.gymify.home.data.local.room_database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.home.data.local.room_database.entity.ExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutExerciseEntity

data class WorkoutExerciseWithExercise(
    @Embedded val workoutExercise: WorkoutExerciseEntity,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id"
    )
    val exercise: ExerciseEntity
)