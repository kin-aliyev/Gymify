package com.example.gymify.home.data.repository

import com.example.gymify.home.data.local.room_database.dao.ExerciseDao
import com.example.gymify.home.data.local.room_database.mapper.toDomain
import com.example.gymify.home.data.local.room_database.mapper.toEntity
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.ExerciseWithStatsInfo
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl(
    private val exerciseDao: ExerciseDao,
//    private val context: Context
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

//    override suspend fun searchExercises(query: String): List<Exercise> {
//        val exerciseNameIds = ExerciseNameMapper.findExercisesByQuery(context, query)
//        if (exerciseNameIds.isEmpty()) return emptyList()

//        val exercises = exerciseDao.getExercisesByNameIds(exerciseNameIds)
//        return exercises.map { it.toDomain() }
//    }

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