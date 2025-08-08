package com.example.gymify.home.domain.repository

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.domain.model.WorkoutPlanRelationSessions
import com.example.gymify.home.domain.model.WorkoutPlanRelationWorkoutExercises
import com.example.gymify.home.domain.model.WorkoutPlanWithFullExercisesInfo
import kotlinx.coroutines.flow.Flow

interface WorkoutPlanRepository {

    suspend fun upsertWorkoutPlan(workoutPlan: WorkoutPlan): Long

    suspend fun deleteWorkoutPlan(workoutPlan: WorkoutPlan)

    suspend fun deleteWorkoutPlanById(workoutId: Int)

    suspend fun getWorkoutPlanById(planId: Int): WorkoutPlan?

    fun getAllWorkoutPlans(): Flow<List<WorkoutPlan>>

    suspend fun getPredefinedWorkoutPlans(): List<WorkoutPlan>

    fun getUserDefinedWorkoutPlans(): Flow<List<WorkoutPlan>>

    suspend fun updateLastUsedDate(workoutPlanId: Int, timestamp: Long)

    suspend fun isWorkoutPlanInDatabase(planId: Int): Boolean

    suspend fun getAllWorkoutPlansByExpertiseLevel(expertiseLevel: ExpertiseLevel): List<WorkoutPlan>

    suspend fun getWorkoutPlansWithWorkoutExercises(): List<WorkoutPlanRelationWorkoutExercises>

    suspend fun getWorkoutPlanWithWorkoutExercises(planId: Int): WorkoutPlanRelationWorkoutExercises?

    suspend fun getWorkoutPlanWithSessions(planId: Int): WorkoutPlanRelationSessions?

    fun getWorkoutPlanWithFullExercises(planId: Int): Flow<WorkoutPlanWithFullExercisesInfo>

    fun getAllWorkoutPlansWithFullExercises(): Flow<List<WorkoutPlanWithFullExercisesInfo>>

}