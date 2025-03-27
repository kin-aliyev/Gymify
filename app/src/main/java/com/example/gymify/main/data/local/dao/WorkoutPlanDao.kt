package com.example.gymify.main.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.main.data.local.entity.WorkoutPlanEntity
import com.example.gymify.main.data.local.entity.relations.WorkoutPlanWithFullExercises
import com.example.gymify.main.data.local.entity.relations.WorkoutPlanWithSessions
import com.example.gymify.main.data.local.entity.relations.helpers.WorkoutPlanWithWorkoutExercises
import kotlinx.coroutines.flow.Flow
import java.time.Instant

@Dao
interface WorkoutPlanDao {

    @Upsert
    suspend fun upsertWorkoutPlan(plan: WorkoutPlanEntity)

    @Delete
    suspend fun deleteWorkoutPlan(plan: WorkoutPlanEntity)

    @Query("DELETE FROM workout_plans WHERE id = :workoutId")
    suspend fun deleteWorkoutPlanById(workoutId: Int)

    @Query("SELECT * FROM workout_plans WHERE id = :id")
    suspend fun getWorkoutPlanById(id: Int): WorkoutPlanEntity?

    @Query("SELECT * FROM workout_plans ORDER BY lastUsedDate DESC")
    suspend fun getAllWorkoutPlans(): List<WorkoutPlanEntity>

    @Query("SELECT * FROM workout_plans WHERE workoutPlanNameId IS NOT NULL")
    suspend fun getPredefinedWorkoutPlans(): List<WorkoutPlanEntity>

    @Query("SELECT * FROM workout_plans WHERE workoutPlanName IS NOT NULL")
    suspend fun getUserDefinedWorkoutPlans(): List<WorkoutPlanEntity>

    @Query("UPDATE workout_plans SET lastUsedDate = :timestamp WHERE id = :workoutPlanId")
    suspend fun updateLastUsedDate(workoutPlanId: Int, timestamp: Instant)

    @Query("SELECT * FROM workout_plans WHERE expertiseLevel = :expertiseLevel")
    suspend fun getAllWorkoutPlansByExpertiseLevel(expertiseLevel: ExpertiseLevel): List<WorkoutPlanEntity>

    // Остальные методы, например, для получения комплексных объектов через @Relation
    @Transaction
    @Query("SELECT * FROM workout_plans")
    suspend fun getWorkoutPlansWithWorkoutExercises(): List<WorkoutPlanWithWorkoutExercises>

    @Transaction
    @Query("SELECT * FROM workout_plans WHERE id = :planId")
    suspend fun  getWorkoutPlanWithWorkoutExercises(planId: Int): WorkoutPlanWithWorkoutExercises?

    @Transaction
    @Query("SELECT * FROM workout_plans WHERE id = :planId")
    suspend fun getWorkoutPlanWithSessions(planId: Int): WorkoutPlanWithSessions?

    @Transaction
    @Query("SELECT * FROM workout_plans WHERE id = :planId")
    fun getWorkoutPlanWithFullExercises(planId: Int): Flow<WorkoutPlanWithFullExercises>

    @Transaction
    @Query("SELECT * FROM workout_plans")
    fun getAllWorkoutPlansWithFullExercises(): Flow<List<WorkoutPlanWithFullExercises>>
}