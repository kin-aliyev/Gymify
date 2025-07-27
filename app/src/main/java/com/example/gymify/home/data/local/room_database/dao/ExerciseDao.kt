package com.example.gymify.home.data.local.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.gymify.home.data.local.room_database.entity.ExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.relations.ExerciseWithStats

@Dao
interface ExerciseDao {

    @Upsert
    suspend fun upsertExercise(exercise: ExerciseEntity)

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getExerciseById(id: Int): ExerciseEntity?

    @Query("SELECT * FROM exercises WHERE exerciseNameId IN (:exerciseNameIds)")
    suspend fun getExercisesByNameIds(exerciseNameIds: List<String>): List<ExerciseEntity>

    @Query("DELETE FROM exercises WHERE id = :exerciseId")
    suspend fun deleteExerciseById(exerciseId: Int)

    @Query("SELECT * FROM exercises WHERE muscleGroup = :muscleGroup")
    suspend fun getExercisesByMuscleGroup(muscleGroup: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises")
    suspend fun getAllExercises(): List<ExerciseEntity>

    @Transaction
    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    suspend fun getExerciseWithStatsById(exerciseId: Int): ExerciseWithStats?

}