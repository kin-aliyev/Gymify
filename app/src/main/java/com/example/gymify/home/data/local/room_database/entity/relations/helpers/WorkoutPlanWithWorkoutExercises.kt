package com.example.gymify.home.data.local.room_database.entity.relations.helpers

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.home.data.local.room_database.entity.WorkoutExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutPlanEntity

data class WorkoutPlanWithWorkoutExercises(
    @Embedded val workoutPlan: WorkoutPlanEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutPlanId"
    )
    val workoutExercises: List<WorkoutExerciseEntity>
)
