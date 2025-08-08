package com.example.gymify.home.data.local.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.home.data.local.room_database.entity.WorkoutPlanEntity
import com.example.gymify.home.data.local.room_database.entity.relations.WorkoutPlanWithFullExercises
import com.example.gymify.home.data.local.room_database.entity.relations.WorkoutPlanWithSessions
import com.example.gymify.home.data.local.room_database.entity.relations.helpers.WorkoutPlanWithWorkoutExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutPlanDao {

    @Upsert
    suspend fun upsertWorkoutPlan(plan: WorkoutPlanEntity): Long

    @Delete
    suspend fun deleteWorkoutPlan(plan: WorkoutPlanEntity)

    @Query("DELETE FROM workout_plans WHERE id = :workoutId")
    suspend fun deleteWorkoutPlanById(workoutId: Int)

    @Query("SELECT * FROM workout_plans WHERE id = :id")
    suspend fun getWorkoutPlanById(id: Int): WorkoutPlanEntity?

    @Query("SELECT * FROM workout_plans ORDER BY lastUsedDate DESC")
    fun getAllWorkoutPlans(): Flow<List<WorkoutPlanEntity>>

    @Query("SELECT * FROM workout_plans WHERE workoutPlanNameId IS NOT NULL")
    suspend fun getPredefinedWorkoutPlans(): List<WorkoutPlanEntity>

    @Query("SELECT * FROM workout_plans WHERE workoutPlanName IS NOT NULL")
    fun getUserDefinedWorkoutPlans(): Flow<List<WorkoutPlanEntity>>

    @Query("UPDATE workout_plans SET lastUsedDate = :timestamp WHERE id = :workoutPlanId")
    suspend fun updateLastUsedDate(workoutPlanId: Int, timestamp: Long)

    @Query("SELECT * FROM workout_plans WHERE expertiseLevel = :expertiseLevel")
    suspend fun getAllWorkoutPlansByExpertiseLevel(expertiseLevel: ExpertiseLevel): List<WorkoutPlanEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM workout_plans WHERE id = :planId)")
    suspend fun isWorkoutPlanInDatabase(planId: Int): Boolean

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