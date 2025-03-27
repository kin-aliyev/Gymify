package com.example.gymify.main.di

import android.app.Application
import androidx.room.Room
import com.example.gymify.main.data.local.GymifyDatabase
import com.example.gymify.main.data.local.converters.ExpertiseLevelConverter
import com.example.gymify.main.data.local.dao.ExerciseDao
import com.example.gymify.main.data.local.dao.ExerciseStatsDao
import com.example.gymify.main.data.local.dao.WorkoutExerciseDao
import com.example.gymify.main.data.local.dao.WorkoutPlanDao
import com.example.gymify.main.data.local.dao.WorkoutSessionDao
import com.example.gymify.main.data.repository.ExerciseRepositoryImpl
import com.example.gymify.main.data.repository.ExerciseStatsRepositoryImpl
import com.example.gymify.main.data.repository.WorkoutExerciseRepositoryImpl
import com.example.gymify.main.data.repository.WorkoutPlanRepositoryImpl
import com.example.gymify.main.data.repository.WorkoutSessionRepositoryImpl
import com.example.gymify.main.domain.repository.ExerciseRepository
import com.example.gymify.main.domain.repository.ExerciseStatsRepository
import com.example.gymify.main.domain.repository.WorkoutExerciseRepository
import com.example.gymify.main.domain.repository.WorkoutPlanRepository
import com.example.gymify.main.domain.repository.WorkoutSessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGymifyDatabase(app: Application): GymifyDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = GymifyDatabase::class.java,
            name = "gymify_database"
        ).addTypeConverter(ExpertiseLevelConverter())
//            .createFromAsset("") // добавить asset в
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(
        exerciseDao: ExerciseDao
    ): ExerciseRepository = ExerciseRepositoryImpl(exerciseDao)

    @Provides
    @Singleton
    fun provideExerciseStatsRepository(
        exerciseStatsDao: ExerciseStatsDao
    ): ExerciseStatsRepository = ExerciseStatsRepositoryImpl(exerciseStatsDao)

    @Provides
    @Singleton
    fun provideWorkoutExerciseRepository(
        workoutExerciseDao: WorkoutExerciseDao
    ): WorkoutExerciseRepository = WorkoutExerciseRepositoryImpl(workoutExerciseDao)

    @Provides
    @Singleton
    fun provideWorkoutPlanRepository(
        workoutPlanDao: WorkoutPlanDao
    ): WorkoutPlanRepository = WorkoutPlanRepositoryImpl(workoutPlanDao)

    @Provides
    fun provideWorkoutSessionRepository(
        workoutSessionDao: WorkoutSessionDao
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