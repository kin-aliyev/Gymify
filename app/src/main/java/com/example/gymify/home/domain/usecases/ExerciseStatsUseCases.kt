package com.example.gymify.home.domain.usecases

import com.example.gymify.home.domain.usecases.exercise_stats.GetExerciseStatsUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.UpdateLastWeightUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.UpdateMaxWeightUseCase
import com.example.gymify.home.domain.usecases.exercise_stats.UpsertExerciseStatsUseCase

data class ExerciseStatsUseCases(
    val upsertExerciseStatsUseCase: UpsertExerciseStatsUseCase,
    val getExerciseStatsUseCase: GetExerciseStatsUseCase,
    val updateLastWeightUseCase: UpdateLastWeightUseCase,
    val updateMaxWeightUseCase: UpdateMaxWeightUseCase
)
