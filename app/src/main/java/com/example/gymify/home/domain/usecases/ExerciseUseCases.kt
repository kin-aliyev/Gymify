package com.example.gymify.home.domain.usecases

import com.example.gymify.home.domain.usecases.exercise.DeleteExerciseByIdUseCase
import com.example.gymify.home.domain.usecases.exercise.DeleteExerciseUseCase
import com.example.gymify.home.domain.usecases.exercise.GetAllExercisesUseCase
import com.example.gymify.home.domain.usecases.exercise.GetExerciseByIdUseCase
import com.example.gymify.home.domain.usecases.exercise.GetExerciseWithStatsByIdUseCase
import com.example.gymify.home.domain.usecases.exercise.GetExercisesByMuscleGroupUseCase
import com.example.gymify.home.domain.usecases.exercise.UpsertExerciseUseCase

data class ExerciseUseCases(
    val upsertExerciseUseCase: UpsertExerciseUseCase,
    val deleteExerciseUseCase: DeleteExerciseUseCase,
    val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    val getExercisesByMuscleGroupUseCase: GetExercisesByMuscleGroupUseCase,
    val getAllExercisesUseCase: GetAllExercisesUseCase,
    val deleteExerciseByIdUseCase: DeleteExerciseByIdUseCase,
    val getExerciseWithStatsByIdUseCase: GetExerciseWithStatsByIdUseCase
)
