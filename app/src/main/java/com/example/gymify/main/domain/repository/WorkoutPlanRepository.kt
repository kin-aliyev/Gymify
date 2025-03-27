package com.example.gymify.main.domain.repository

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.main.data.local.entity.WorkoutPlanEntity
import com.example.gymify.main.domain.model.WorkoutPlan
import com.example.gymify.main.domain.model.WorkoutPlanWithFullExercisesInfo
import com.example.gymify.main.domain.model.WorkoutPlanRelationSessions
import com.example.gymify.main.domain.model.WorkoutPlanRelationWorkoutExercises
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface WorkoutPlanRepository {

    suspend fun upsertWorkoutPlan(workoutPlan: WorkoutPlan)

    suspend fun deleteWorkoutPlan(workoutPlan: WorkoutPlan)

    suspend fun deleteWorkoutPlanById(workoutId: Int)

    suspend fun getWorkoutPlanById(planId: Int): WorkoutPlan?

    suspend fun getAllWorkoutPlans(): List<WorkoutPlan>

    suspend fun getPredefinedWorkoutPlans(): List<WorkoutPlan>

    suspend fun getUserDefinedWorkoutPlans(): List<WorkoutPlan>

    suspend fun updateLastUsedDate(workoutPlanId: Int, timestamp: Instant)

    suspend fun getAllWorkoutPlansByExpertiseLevel(expertiseLevel: ExpertiseLevel): List<WorkoutPlan>

    suspend fun getWorkoutPlansWithWorkoutExercises(): List<WorkoutPlanRelationWorkoutExercises>

    suspend fun getWorkoutPlanWithWorkoutExercises(planId: Int): WorkoutPlanRelationWorkoutExercises?

    suspend fun getWorkoutPlanWithSessions(planId: Int): WorkoutPlanRelationSessions?

    fun getWorkoutPlanWithFullExercises(planId: Int): Flow<WorkoutPlanWithFullExercisesInfo>

    fun getAllWorkoutPlansWithFullExercises(): Flow<List<WorkoutPlanWithFullExercisesInfo>>

}