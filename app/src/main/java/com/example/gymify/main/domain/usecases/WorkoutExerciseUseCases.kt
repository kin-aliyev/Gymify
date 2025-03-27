package com.example.gymify.main.domain.usecases

import com.example.gymify.main.domain.usecases.workout_exercise.DeleteWorkoutExerciseByIdUseCase
import com.example.gymify.main.domain.usecases.workout_exercise.DeleteWorkoutExerciseUseCase
import com.example.gymify.main.domain.usecases.workout_exercise.GetFullExercisesForWorkoutPlanUseCase
import com.example.gymify.main.domain.usecases.workout_exercise.GetWorkoutExerciseByIdUseCase
import com.example.gymify.main.domain.usecases.workout_exercise.GetWorkoutExerciseByPlanIdUseCase
import com.example.gymify.main.domain.usecases.workout_exercise.GetWorkoutExerciseWithExerciseUseCase
import com.example.gymify.main.domain.usecases.workout_exercise.UpsertWorkoutExerciseUseCase

data class WorkoutExerciseUseCases(
    val upsertWorkoutExerciseUseCase: UpsertWorkoutExerciseUseCase,
    val deleteWorkoutExerciseUseCase: DeleteWorkoutExerciseUseCase,
    val getWorkoutExerciseByPlanIdUseCase: GetWorkoutExerciseByPlanIdUseCase,
    val getWorkoutExerciseWithExerciseUseCase: GetWorkoutExerciseWithExerciseUseCase,
    val deleteWorkoutExerciseByIdUseCase: DeleteWorkoutExerciseByIdUseCase,
    val getWorkoutExerciseByIdUseCase: GetWorkoutExerciseByIdUseCase,
    val getFullExercisesForWorkoutPlanUseCase: GetFullExercisesForWorkoutPlanUseCase
)