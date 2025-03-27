package com.example.gymify.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymify.main.data.local.converters.ExpertiseLevelConverter
import com.example.gymify.main.data.local.converters.InstantConverter
import com.example.gymify.main.data.local.converters.MuscleGroupConverter
import com.example.gymify.main.data.local.dao.ExerciseDao
import com.example.gymify.main.data.local.dao.ExerciseStatsDao
import com.example.gymify.main.data.local.dao.WorkoutExerciseDao
import com.example.gymify.main.data.local.dao.WorkoutPlanDao
import com.example.gymify.main.data.local.dao.WorkoutSessionDao
import com.example.gymify.main.data.local.entity.ExerciseEntity
import com.example.gymify.main.data.local.entity.ExerciseStatsEntity
import com.example.gymify.main.data.local.entity.WorkoutExerciseEntity
import com.example.gymify.main.data.local.entity.WorkoutPlanEntity
import com.example.gymify.main.data.local.entity.WorkoutSessionEntity

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
@TypeConverters(ExpertiseLevelConverter::class, MuscleGroupConverter::class, InstantConverter::class)
abstract class GymifyDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseStatsDao(): ExerciseStatsDao
    abstract fun workoutExerciseDao(): WorkoutExerciseDao
    abstract fun workoutPlanDao(): WorkoutPlanDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
}