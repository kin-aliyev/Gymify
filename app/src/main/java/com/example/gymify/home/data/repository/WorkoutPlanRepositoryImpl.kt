package com.example.gymify.home.data.repository

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.data.local.room_database.dao.WorkoutPlanDao
import com.example.gymify.home.data.local.room_database.mapper.toDomain
import com.example.gymify.home.data.local.room_database.mapper.toEntity
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.model.WorkoutPlanWithFullExercisesInfo
import com.example.gymify.home.domain.model.WorkoutPlanRelationSessions
import com.example.gymify.home.domain.model.WorkoutPlanRelationWorkoutExercises
import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkoutPlanRepositoryImpl(
    private val workoutPlanDao: WorkoutPlanDao
): WorkoutPlanRepository {
    override suspend fun upsertWorkoutPlan(workoutPlan: WorkoutPlan): Long {
        return workoutPlanDao.upsertWorkoutPlan(workoutPlan.toEntity())
    }

    override suspend fun deleteWorkoutPlan(workoutPlan: WorkoutPlan) {
        workoutPlanDao.deleteWorkoutPlan(workoutPlan.toEntity())
    }

    override suspend fun deleteWorkoutPlanById(workoutId: Int) {
        workoutPlanDao.deleteWorkoutPlanById(workoutId)
    }

    override suspend fun getWorkoutPlanById(planId: Int): WorkoutPlan? {
        return workoutPlanDao.getWorkoutPlanById(planId)?.toDomain()
    }

    override suspend fun getAllWorkoutPlans(): List<WorkoutPlan> {
        return workoutPlanDao.getAllWorkoutPlans().map { it.toDomain() }
    }

    override suspend fun getPredefinedWorkoutPlans(): List<WorkoutPlan> {
        return workoutPlanDao.getPredefinedWorkoutPlans().map { it.toDomain() }
    }

    override suspend fun getUserDefinedWorkoutPlans(): List<WorkoutPlan> {
        return workoutPlanDao.getUserDefinedWorkoutPlans().map { it.toDomain() }
    }

    override suspend fun updateLastUsedDate(workoutPlanId: Int, timestamp: Long) {
        workoutPlanDao.updateLastUsedDate(workoutPlanId, timestamp)
    }

    override suspend fun isWorkoutPlanInDatabase(planId: Int): Boolean {
        return workoutPlanDao.isWorkoutPlanInDatabase(planId)
    }

    override suspend fun getAllWorkoutPlansByExpertiseLevel(expertiseLevel: ExpertiseLevel): List<WorkoutPlan> {
        return workoutPlanDao.getAllWorkoutPlansByExpertiseLevel(expertiseLevel).map { it.toDomain() }
    }

    override suspend fun getWorkoutPlansWithWorkoutExercises(): List<WorkoutPlanRelationWorkoutExercises> {
        return workoutPlanDao.getWorkoutPlansWithWorkoutExercises().map { it.toDomain() }
    }

    override suspend fun getWorkoutPlanWithWorkoutExercises(planId: Int): WorkoutPlanRelationWorkoutExercises? {
        return workoutPlanDao.getWorkoutPlanWithWorkoutExercises(planId)?.toDomain()
    }

    override suspend fun getWorkoutPlanWithSessions(planId: Int): WorkoutPlanRelationSessions? {
        return workoutPlanDao.getWorkoutPlanWithSessions(planId)?.toDomain()
    }

    override fun getWorkoutPlanWithFullExercises(planId: Int): Flow<WorkoutPlanWithFullExercisesInfo> {
        return workoutPlanDao.getWorkoutPlanWithFullExercises(planId).map { it.toDomain() }
    }

    override fun getAllWorkoutPlansWithFullExercises(): Flow<List<WorkoutPlanWithFullExercisesInfo>> {
        return workoutPlanDao.getAllWorkoutPlansWithFullExercises().map { list ->
            list.map { it.toDomain() }
        }
    }
}