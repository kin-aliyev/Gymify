package com.example.gymify.home.di

import android.content.Context
import androidx.room.Room
import com.example.gymify.home.data.local.room_database.GymifyDatabase
import com.example.gymify.home.data.local.room_database.dao.ExerciseDao
import com.example.gymify.home.data.local.room_database.dao.ExerciseStatsDao
import com.example.gymify.home.data.local.room_database.dao.WorkoutExerciseDao
import com.example.gymify.home.data.local.room_database.dao.WorkoutPlanDao
import com.example.gymify.home.data.local.room_database.dao.WorkoutSessionDao
import com.example.gymify.home.data.repository.ExerciseRepositoryImpl
import com.example.gymify.home.data.repository.ExerciseStatsRepositoryImpl
import com.example.gymify.home.data.repository.WorkoutExerciseRepositoryImpl
import com.example.gymify.home.data.repository.WorkoutPlanRepositoryImpl
import com.example.gymify.home.data.repository.WorkoutSessionRepositoryImpl
import com.example.gymify.home.domain.repository.ExerciseRepository
import com.example.gymify.home.domain.repository.ExerciseStatsRepository
import com.example.gymify.home.domain.repository.WorkoutExerciseRepository
import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import com.example.gymify.home.domain.repository.WorkoutSessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGymifyDatabase(
        @ApplicationContext context: Context
//        app: Application,
    ): GymifyDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = GymifyDatabase::class.java,
            name = "gymify_database"
        ).createFromAsset("database/exercises.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(
        exerciseDao: ExerciseDao,
    ): ExerciseRepository = ExerciseRepositoryImpl(exerciseDao)

    @Provides
    @Singleton
    fun provideExerciseStatsRepository(
        exerciseStatsDao: ExerciseStatsDao,
    ): ExerciseStatsRepository = ExerciseStatsRepositoryImpl(exerciseStatsDao)

    @Provides
    @Singleton
    fun provideWorkoutExerciseRepository(
        workoutExerciseDao: WorkoutExerciseDao,
    ): WorkoutExerciseRepository = WorkoutExerciseRepositoryImpl(workoutExerciseDao)

    @Provides
    @Singleton
    fun provideWorkoutPlanRepository(
        workoutPlanDao: WorkoutPlanDao,
    ): WorkoutPlanRepository = WorkoutPlanRepositoryImpl(workoutPlanDao)

    @Provides
    fun provideWorkoutSessionRepository(
        workoutSessionDao: WorkoutSessionDao,
    ): WorkoutSessionRepository = WorkoutSessionRepositoryImpl(workoutSessionDao)

    @Provides
    fun provideExerciseDao(db: GymifyDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    fun provideExerciseStatsDao(db: GymifyDatabase): ExerciseStatsDao = db.exerciseStatsDao()

    @Provides
    fun provideWorkoutExerciseDao(db: GymifyDatabase): WorkoutExerciseDao = db.workoutExerciseDao()

    @Provides
    fun provideWorkoutPlanDao(db: GymifyDatabase): WorkoutPlanDao = db.workoutPlanDao()

    @Provides
    fun provideWorkoutSessionDao(db: GymifyDatabase): WorkoutSessionDao = db.workoutSessionDao()

}