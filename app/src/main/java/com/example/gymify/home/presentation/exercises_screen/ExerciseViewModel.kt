package com.example.gymify.home.presentation.exercises_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.util.ExerciseNameMapper
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.domain.usecases.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val exerciseUseCases: ExerciseUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(ExercisesState())
    val state: State<ExercisesState> = _state

    fun onAction(action: ExercisesAction) {
        when (action) {
            is ExercisesAction.OnClearSearch -> clearSearch()
            is ExercisesAction.OnExerciseClick -> {
                // Навигация обрабатывается в NavGraph
            }
            is ExercisesAction.OnSearchTextChange -> searchExercise(action.searchText)
        }
    }

    fun loadExercises(muscleGroup: MuscleGroup) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                selectedMuscleGroup = muscleGroup,
            )

            try {
                val exercises = exerciseUseCases.getExercisesByMuscleGroupUseCase(muscleGroup)
                _state.value = _state.value.copy(
                    exercises = exercises,
                    filteredExercises = exercises,
                )
            } catch (e: Exception) {
                Log.e("Exercise View Model", "Exception is $e")
            }
        }
    }

    private fun clearSearch() {
        _state.value = _state.value.copy(
            searchText = "",
            filteredExercises = _state.value.exercises
        )
    }

    private fun searchExercise(searchText: String) {
        val filteredExercises = if (searchText.isEmpty()) {
            _state.value.exercises
        } else {
            _state.value.exercises.filter {
                val exerciseName = context.getString(
                    ExerciseNameMapper.getName(it.exerciseNameId)
                )
                exerciseName.contains(searchText, ignoreCase = true)
            }
        }

        _state.value = _state.value.copy(
            searchText = searchText,
            filteredExercises = filteredExercises
        )
    }

}