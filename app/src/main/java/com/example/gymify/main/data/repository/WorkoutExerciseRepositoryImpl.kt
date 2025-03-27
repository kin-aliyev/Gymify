package com.example.gymify.main.data.repository

import com.example.gymify.main.data.local.dao.WorkoutExerciseDao
import com.example.gymify.main.data.local.mapper.toDomain
import com.example.gymify.main.data.local.mapper.toEntity
import com.example.gymify.main.domain.model.WorkoutExercise
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.main.domain.repository.WorkoutExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkoutExerciseRepositoryImpl(
    private val workoutExerciseDao: WorkoutExerciseDao
): WorkoutExerciseRepository {
    override suspend fun upsertWorkoutExercise(workoutExercise: WorkoutExercise) {
        workoutExerciseDao.upsertWorkoutExerciseEntity(workoutExercise.toEntity())
    }

    override suspend fun deleteWorkoutExercise(workoutExercise: WorkoutExercise) {
        workoutExerciseDao.deleteWorkoutExerciseEntity(workoutExercise.toEntity())
    }

    override suspend fun deleteWorkoutExerciseById(workoutExerciseId: Int) {
        workoutExerciseDao.deleteWorkoutExerciseById(workoutExerciseId)
    }

    override suspend fun getWorkoutExercisesByPlanId(planId: Int): List<WorkoutExercise> {
        return workoutExerciseDao.getWorkoutExercisesByPlanId(planId).map { it.toDomain() }
    }

    override suspend fun getWorkoutExerciseById(workoutExerciseId: Int): WorkoutExercise? {
        return workoutExerciseDao.getWorkoutExerciseById(workoutExerciseId)?.toDomain()
    }

    override suspend fun getWorkoutExerciseWithExercise(workoutExerciseId: Int): WorkoutExerciseWithExerciseInfo? {
        return workoutExerciseDao.getWorkoutExerciseWithExercise(workoutExerciseId)?.toDomain()
    }


    override suspend fun getFullExercisesForWorkoutPlan(planId: Int): Flow<List<WorkoutExerciseWithExerciseInfo>> {
        return workoutExerciseDao.getFullExercisesForWorkoutPlan(planId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

}