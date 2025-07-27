package com.example.gymify.home.domain.usecases

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

data class WorkoutPlanUseCases(
    val upsertWorkoutPlanUseCase: UpsertWorkoutPlanUseCase,
    val deleteWorkoutPlanUseCase: DeleteWorkoutPlanUseCase,
    val getWorkoutPlanByIdUseCase: GetWorkoutPlanByIdUseCase,
    val getAllWorkoutPlansUseCase: GetAllWorkoutPlansUseCase,
    val getWorkoutPlansWithWorkoutExercisesUseCase: GetWorkoutPlansWithWorkoutExercisesUseCase,
    val getWorkoutPlanWithWorkoutExercisesUseCase: GetWorkoutPlanWithWorkoutExercisesUseCase,
    val getWorkoutPlanWithSessionsUseCase: GetWorkoutPlanWithSessionsUseCase,
    val getWorkoutPlanWithFullExercisesUseCase: GetWorkoutPlanWithFullExercisesUseCase,
    val getAllWorkoutPlansWithFullExercisesUseCase: GetAllWorkoutPlansWithFullExercisesUseCase,
    val getAllWorkoutPlansByExpertiseLevel: GetAllWorkoutPlansByExpertiseLevel,
    val deleteWorkoutPlanByIdUseCase: DeleteWorkoutPlanByIdUseCase,
    val getPredefinedWorkoutPlansUseCase: GetPredefinedWorkoutPlansUseCase,
    val getUserDefinedWorkoutPlansUseCase: GetUserDefinedWorkoutPlansUseCase,
    val updateLastUsedDateUseCase: UpdateLastUsedDateUseCase,
    val isWorkoutPlanInDatabaseUseCase: IsWorkoutPlanInDatabaseUseCase
)
