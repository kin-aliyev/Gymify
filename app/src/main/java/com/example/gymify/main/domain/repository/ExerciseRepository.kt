package com.example.gymify.main.domain.repository

import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.model.ExerciseWithStatsInfo
import com.example.gymify.main.domain.model.MuscleGroup

interface ExerciseRepository {

    suspend fun upsertExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)

    suspend fun getExerciseById(id: Int): Exercise?

    suspend fun deleteExerciseById(exerciseId: Int)

    suspend fun getExercisesByMuscleGroup(muscleGroup: MuscleGroup): List<Exercise>

    suspend fun getAllExercises(): List<Exercise>

    suspend fun getExerciseWithStatsById(exerciseId: Int): ExerciseWithStatsInfo?

}