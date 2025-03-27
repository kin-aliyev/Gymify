package com.example.gymify.main.domain.usecases

import com.example.gymify.main.domain.usecases.workout_session.DeleteWorkoutSessionByIdUseCase
import com.example.gymify.main.domain.usecases.workout_session.GetSessionsByPlanIdUseCase
import com.example.gymify.main.domain.usecases.workout_session.GetSessionsByTimeRangeUseCase
import com.example.gymify.main.domain.usecases.workout_session.GetWorkoutSessionByIdUseCase
import com.example.gymify.main.domain.usecases.workout_session.UpsertWorkoutSessionUseCase

data class WorkoutSessionUseCases(
    val upsertWorkoutSessionUseCase: UpsertWorkoutSessionUseCase,
    val deleteWorkoutSessionUseCase: DeleteWorkoutSessionByIdUseCase,
    val getSessionsByPlanIdUseCase: GetSessionsByPlanIdUseCase,
    val getSessionsByTimeRangeUseCase: GetSessionsByTimeRangeUseCase,
    val getWorkoutSessionByIdUseCase: GetWorkoutSessionByIdUseCase
)
