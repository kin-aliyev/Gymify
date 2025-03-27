package com.example.gymify.main.domain.repository

import com.example.gymify.main.domain.model.WorkoutExercise
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo
import kotlinx.coroutines.flow.Flow

interface WorkoutExerciseRepository {

    suspend fun upsertWorkoutExercise(workoutExercise: WorkoutExercise)

    suspend fun deleteWorkoutExercise(workoutExercise: WorkoutExercise)

    suspend fun deleteWorkoutExerciseById(workoutExerciseId: Int)

    suspend fun getWorkoutExercisesByPlanId(planId: Int): List<WorkoutExercise>

    suspend fun getWorkoutExerciseById(workoutExerciseId: Int): WorkoutExercise?

    suspend fun getWorkoutExerciseWithExercise(workoutExerciseId: Int): WorkoutExerciseWithExerciseInfo?

    suspend fun getFullExercisesForWorkoutPlan(planId: Int): Flow<List<WorkoutExerciseWithExerciseInfo>>
}