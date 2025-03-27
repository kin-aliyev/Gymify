package com.example.gymify.main.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.main.data.local.entity.WorkoutExerciseEntity
import com.example.gymify.main.data.local.entity.WorkoutPlanEntity

data class WorkoutPlanWithFullExercises(
    @Embedded val workoutPlan: WorkoutPlanEntity,
    @Relation(
        entity = WorkoutExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutPlanId"
    )
    val workoutExercisesWithExercises: List<WorkoutExerciseWithExercise>
)
