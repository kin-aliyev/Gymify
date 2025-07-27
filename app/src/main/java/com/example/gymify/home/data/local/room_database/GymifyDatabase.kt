package com.example.gymify.home.data.local.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymify.home.data.local.room_database.converters.ExpertiseLevelConverter
import com.example.gymify.home.data.local.room_database.converters.MuscleGroupConverter
import com.example.gymify.home.data.local.room_database.dao.ExerciseDao
import com.example.gymify.home.data.local.room_database.dao.ExerciseStatsDao
import com.example.gymify.home.data.local.room_database.dao.WorkoutExerciseDao
import com.example.gymify.home.data.local.room_database.dao.WorkoutPlanDao
import com.example.gymify.home.data.local.room_database.dao.WorkoutSessionDao
import com.example.gymify.home.data.local.room_database.entity.ExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.ExerciseStatsEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutExerciseEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutPlanEntity
import com.example.gymify.home.data.local.room_database.entity.WorkoutSessionEntity

@Database(
    entities = [
        ExerciseEntity::class,
        ExerciseStatsEntity::class,
        WorkoutExerciseEntity::class,
        WorkoutPlanEntity::class,
        WorkoutSessionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ExpertiseLevelConverter::class, MuscleGroupConverter::class)
abstract class GymifyDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseStatsDao(): ExerciseStatsDao
    abstract fun workoutExerciseDao(): WorkoutExerciseDao
    abstract fun workoutPlanDao(): WorkoutPlanDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
}