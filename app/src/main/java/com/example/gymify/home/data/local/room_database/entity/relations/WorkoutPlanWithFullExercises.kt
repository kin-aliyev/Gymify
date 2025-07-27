package com.example.gymify.home.data.local.room_database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gymify.home.data.local.room_database.entity.WorkoutExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutPlanEntity

data class WorkoutPlanWithFullExercises(
    @Embedded val workoutPlan: WorkoutPlanEntity,
    @Relation(
        entity = WorkoutExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutPlanId"
    )
    val workoutExercisesWithExercises: List<WorkoutExerciseWithExercise>
)
