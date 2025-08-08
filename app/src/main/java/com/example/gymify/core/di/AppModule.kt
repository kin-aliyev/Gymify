package com.example.gymify.core.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gymify.core.data.manager.AppCoreManagerImpl
import com.example.gymify.core.data.manager.LocalUserInfoManagerImpl
import com.example.gymify.core.data.manager.AppLocaleManager
import com.example.gymify.core.domain.manager.AppCoreManager
import com.example.gymify.core.domain.usecases.AppCoreUseCases
import com.example.gymify.core.domain.usecases.language.GetLanguageUseCase
import com.example.gymify.core.domain.usecases.language.SetLanguageUseCase
import com.example.gymify.core.domain.usecases.registration.ReadRegistrationStatusUseCase
import com.example.gymify.core.domain.usecases.registration.SaveRegistrationUseCase
import com.example.gymify.core.domain.usecases.theme_mode.GetThemeModeUseCase
import com.example.gymify.core.domain.usecases.theme_mode.SetThemeModeUseCase
import com.example.gymify.home.util.TimeRangeUtils
import com.example.gymify.home.domain.repository.ExerciseRepository
import com.example.gymify.home.domain.repository.ExerciseStatsRepository
import com.example.gymify.home.domain.repository.WorkoutExerciseRepository
import com.example.gymify.home.domain.repository.WorkoutPlanRepository
import com.example.gymify.home.domain.repository.WorkoutSessionRepository
import com.example.gymify.home.domain.usecases.ExerciseStatsUseCases
import com.example.gymify.home.domain.usecases.ExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutExerciseUseCases
import com.example.gymify.home.domain.usecases.WorkoutPlanUseCases
import com.example.gymify.home.domain.usecases.WorkoutSessionUseCases
import com.example.gymify.home.domain.usecases.exercise.DeleteExerciseByIdUseCase
import com.example.gymify.home.domain.usecases.exercise.DeleteExerciseUseCase
import com.example.gymify.home.domain.usecases.exercise.GetAllExercisesUseCase
import com.example.gymify.home.domain.usecases.exercise.GetExerciseByIdUseCase
import com.example.gymify.home.domain.usecases.exercise.GetExerciseWithStatsByIdUseCase
import com.example.gymify.home.domain.usecases.exercise.GetExercisesByMuscleGroupUseCase
import com.example.gymify.home.domain.usecases.exercise.UpsertExerciseUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.GetExerciseStatsUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.UpdateLastWeightUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.UpdateMaxWeightUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.UpsertExerciseStatsUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.DeleteWorkoutExerciseByIdUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.DeleteWorkoutExerciseUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.GetFullExercisesForWorkoutPlanUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.GetWorkoutExerciseByIdUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.GetWorkoutExercisesByPlanIdUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.GetWorkoutExerciseWithExerciseUseCase
import com.example.gymify.home.domain.usecases.workout_exercise.UpsertWorkoutExerciseUseCase
import com.example.gymify.home.domain.usecases.workout_plan.DeleteWorkoutPlanByIdUseCase
import com.example.gymify.home.domain.usecases.workout_plan.DeleteWorkoutPlanUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetAllWorkoutPlansByExpertiseLevel
import com.example.gymify.home.domain.usecases.workout_plan.GetAllWorkoutPlansUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetAllWorkoutPlansWithFullExercisesUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetPredefinedWorkoutPlansUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetUserDefinedWorkoutPlansUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetWorkoutPlanByIdUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetWorkoutPlanWithFullExercisesUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetWorkoutPlanWithSessionsUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetWorkoutPlanWithWorkoutExercisesUseCase
import com.example.gymify.home.domain.usecases.workout_plan.GetWorkoutPlansWithWorkoutExercisesUseCase
import com.example.gymify.home.domain.usecases.workout_plan.IsWorkoutPlanInDatabaseUseCase
import com.example.gymify.home.domain.usecases.workout_plan.UpdateLastUsedDateUseCase
import com.example.gymify.home.domain.usecases.workout_plan.UpsertWorkoutPlanUseCase
import com.example.gymify.home.domain.usecases.workout_session.DeleteWorkoutSessionByIdUseCase
import com.example.gymify.home.domain.usecases.workout_session.GetSessionsByPlanIdUseCase
import com.example.gymify.home.domain.usecases.workout_session.GetSessionsByTimeRangeUseCase
import com.example.gymify.home.domain.usecases.workout_session.GetWorkoutSessionByIdUseCase
import com.example.gymify.home.domain.usecases.workout_session.UpsertWorkoutSessionUseCase
import com.example.gymify.core.domain.manager.LocalUserInfoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserInfoManager(
        @ApplicationContext context: Context
    ): LocalUserInfoManager = LocalUserInfoManagerImpl(context)

    @Provides
    @Singleton
    fun provideAppCoreManager(
        @ApplicationContext context: Context
    ): AppCoreManager = AppCoreManagerImpl(context)

    @Provides
    @Singleton
    fun provideLocaleManager(): AppLocaleManager = AppLocaleManager()

    @Provides
    @Singleton
    fun provideGetLanguageUseCase(appCoreManager: AppCoreManager): GetLanguageUseCase {
        return GetLanguageUseCase(appCoreManager)
    }

    @Provides
    @Singleton
    fun provideSetLanguageUseCase(appCoreManager: AppCoreManager): SetLanguageUseCase {
        return SetLanguageUseCase(appCoreManager)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideTimeRangeUtils(): TimeRangeUtils = TimeRangeUtils()

    @Provides
    @Singleton
    fun provideAppCoreUseCases(
        appCoreManager: AppCoreManager
    ): AppCoreUseCases = AppCoreUseCases(
        saveRegistrationUseCase = SaveRegistrationUseCase(appCoreManager),
        readRegistrationStatusUseCase = ReadRegistrationStatusUseCase(appCoreManager),

        getThemeModeUseCase = GetThemeModeUseCase(appCoreManager),
        setThemeModeUseCase = SetThemeModeUseCase(appCoreManager),

        getLanguageUseCase = GetLanguageUseCase(appCoreManager),
        setLanguageUseCase = SetLanguageUseCase(appCoreManager)
    )

    @Provides
    @Singleton
    fun provideExerciseUseCases(
        repository: ExerciseRepository
    ): ExerciseUseCases = ExerciseUseCases(
        upsertExerciseUseCase = UpsertExerciseUseCase(repository),
        deleteExerciseUseCase = DeleteExerciseUseCase(repository),
        deleteExerciseByIdUseCase = DeleteExerciseByIdUseCase(repository),

        getExerciseByIdUseCase = GetExerciseByIdUseCase(repository),
        getExercisesByMuscleGroupUseCase = GetExercisesByMuscleGroupUseCase(repository),

        getAllExercisesUseCase = GetAllExercisesUseCase(repository),

        getExerciseWithStatsByIdUseCase = GetExerciseWithStatsByIdUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideExerciseStatsUseCases(
        repository: ExerciseStatsRepository
    ): ExerciseStatsUseCases = ExerciseStatsUseCases(
        upsertExerciseStatsUseCase = UpsertExerciseStatsUseCase(repository),
        getExerciseStatsUseCase = GetExerciseStatsUseCase(repository),

        updateMaxWeightUseCase = UpdateMaxWeightUseCase(repository),
        updateLastWeightUseCase = UpdateLastWeightUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideWorkoutExerciseUseCases(
        repository: WorkoutExerciseRepository
    ): WorkoutExerciseUseCases = WorkoutExerciseUseCases(
        getWorkoutExerciseByIdUseCase = GetWorkoutExerciseByIdUseCase(repository),
        getWorkoutExercisesByPlanIdUseCase = GetWorkoutExercisesByPlanIdUseCase(repository),
        upsertWorkoutExerciseUseCase = UpsertWorkoutExerciseUseCase(repository),
        deleteWorkoutExerciseUseCase = DeleteWorkoutExerciseUseCase(repository),
        deleteWorkoutExerciseByIdUseCase = DeleteWorkoutExerciseByIdUseCase(repository),

        getWorkoutExerciseWithExerciseUseCase = GetWorkoutExerciseWithExerciseUseCase(repository),
        getFullExercisesForWorkoutPlanUseCase = GetFullExercisesForWorkoutPlanUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideWorkoutPlanUseCases(
        repository: WorkoutPlanRepository
    ): WorkoutPlanUseCases = WorkoutPlanUseCases(
        upsertWorkoutPlanUseCase = UpsertWorkoutPlanUseCase(repository),
        deleteWorkoutPlanUseCase = DeleteWorkoutPlanUseCase(repository),
        getWorkoutPlanByIdUseCase = GetWorkoutPlanByIdUseCase(repository),
        getAllWorkoutPlansUseCase = GetAllWorkoutPlansUseCase(repository),
        getWorkoutPlanWithWorkoutExercisesUseCase = GetWorkoutPlanWithWorkoutExercisesUseCase(repository),
        getWorkoutPlansWithWorkoutExercisesUseCase = GetWorkoutPlansWithWorkoutExercisesUseCase(repository),
        getWorkoutPlanWithSessionsUseCase = GetWorkoutPlanWithSessionsUseCase(repository),
        getWorkoutPlanWithFullExercisesUseCase = GetWorkoutPlanWithFullExercisesUseCase(repository),
        getAllWorkoutPlansWithFullExercisesUseCase = GetAllWorkoutPlansWithFullExercisesUseCase(repository),
        getAllWorkoutPlansByExpertiseLevel = GetAllWorkoutPlansByExpertiseLevel(repository),
        getPredefinedWorkoutPlansUseCase = GetPredefinedWorkoutPlansUseCase(repository),
        deleteWorkoutPlanByIdUseCase = DeleteWorkoutPlanByIdUseCase(repository),
        getUserDefinedWorkoutPlansUseCase = GetUserDefinedWorkoutPlansUseCase(repository),
        updateLastUsedDateUseCase = UpdateLastUsedDateUseCase(repository),
        isWorkoutPlanInDatabaseUseCase = IsWorkoutPlanInDatabaseUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideWorkoutSessionUseCases(
        repository: WorkoutSessionRepository
    ): WorkoutSessionUseCases = WorkoutSessionUseCases(
        upsertWorkoutSessionUseCase = UpsertWorkoutSessionUseCase(repository),
        deleteWorkoutSessionUseCase = DeleteWorkoutSessionByIdUseCase(repository),
        getSessionsByPlanIdUseCase = GetSessionsByPlanIdUseCase(repository),
        getSessionsByTimeRangeUseCase = GetSessionsByTimeRangeUseCase(repository),
        getWorkoutSessionByIdUseCase = GetWorkoutSessionByIdUseCase(repository)
    )

}