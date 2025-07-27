package com.example.gymify.home.presentation.exercises_screen

import com.example.gymify.home.domain.model.Exercise

sealed interface ExercisesAction {
    data class OnExerciseClick(val exercise: Exercise) : ExercisesAction
    object OnClearSearch : ExercisesAction
    data class OnSearchTextChange(val searchText: String) : ExercisesAction
    // TODO добавить для добавления изображения пользовательского
}