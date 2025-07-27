package com.example.gymify.home.presentation.exercises_screen

import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.MuscleGroup

data class ExercisesState(
    val selectedMuscleGroup: MuscleGroup? = null,
    val exercises: List<Exercise> = emptyList(),
    val filteredExercises: List<Exercise> = emptyList(),
    val searchText: String = "",
)
