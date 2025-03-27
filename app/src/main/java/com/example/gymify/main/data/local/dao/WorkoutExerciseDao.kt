package com.example.gymify.main.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.gymify.main.data.local.entity.WorkoutExerciseEntity
import com.example.gymify.main.data.local.entity.relations.WorkoutExerciseWithExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    @Upsert
    suspend fun upsertWorkoutExerciseEntity(workoutExerciseEntity: WorkoutExerciseEntity)

    @Delete
    suspend fun deleteWorkoutExerciseEntity(workoutExerciseEntity: WorkoutExerciseEntity)

    @Query("DELETE FROM workout_exercises WHERE id = :workoutExerciseId")
    suspend fun deleteWorkoutExerciseById(workoutExerciseId: Int)

    @Query("SELECT * FROM workout_exercises WHERE workoutPlanId = :workoutPlanId")
    suspend fun getWorkoutExercisesByPlanId(workoutPlanId: Int): List<WorkoutExerciseEntity>

    @Query("SELECT * FROM workout_exercises WHERE id = :id")
    suspend fun getWorkoutExerciseById(id: Int): WorkoutExerciseEntity?

    @Transaction
    @Query("SELECT * FROM workout_exercises WHERE id = :workoutExerciseId")
    suspend fun getWorkoutExerciseWithExercise(workoutExerciseId: Int): WorkoutExerciseWithExercise?

    @Transaction
    @Query("SELECT * FROM workout_exercises WHERE workoutPlanId = :planId")
    fun getFullExercisesForWorkoutPlan(planId: Int): Flow<List<WorkoutExerciseWithExercise>>
}
