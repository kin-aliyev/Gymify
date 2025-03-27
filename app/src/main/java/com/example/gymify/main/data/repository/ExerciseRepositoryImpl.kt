package com.example.gymify.main.data.repository

import com.example.gymify.main.data.local.dao.ExerciseDao
import com.example.gymify.main.data.local.mapper.toDomain
import com.example.gymify.main.data.local.mapper.toEntity
import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.model.ExerciseWithStatsInfo
import com.example.gymify.main.domain.model.MuscleGroup
import com.example.gymify.main.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl(
    private val exerciseDao: ExerciseDao
): ExerciseRepository {
    override suspend fun upsertExercise(exercise: Exercise) {
        exerciseDao.upsertExercise(exercise.toEntity())
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise.toEntity())
    }

    override suspend fun getExerciseById(id: Int): Exercise? {
        return exerciseDao.getExerciseById(id)?.toDomain()
    }

    override suspend fun deleteExerciseById(exerciseId: Int) {
        exerciseDao.deleteExerciseById(exerciseId)
    }

    override suspend fun getExercisesByMuscleGroup(muscleGroup: MuscleGroup): List<Exercise> {
        return exerciseDao.getExercisesByMuscleGroup(muscleGroup.name).map { it.toDomain() }
    }

    override suspend fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises().map { it.toDomain() }
    }

    override suspend fun getExerciseWithStatsById(exerciseId: Int): ExerciseWithStatsInfo? {
        return exerciseDao.getExerciseWithStatsById(exerciseId)?.toDomain()
    }


}