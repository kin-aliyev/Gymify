package com.example.gymify.main.data.local.entity.relations.helpers

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.main.data.local.entity.WorkoutExerciseEntity
import com.example.gymify.main.data.local.entity.WorkoutPlanEntity

data class WorkoutPlanWithWorkoutExercises(
    @Embedded val workoutPlan: WorkoutPlanEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutPlanId"
    )
    val workoutExercises: List<WorkoutExerciseEntity>
)
