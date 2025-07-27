package com.example.gymify.home.presentation.analytics_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.util.TimeRangeUtils
import com.example.gymify.home.domain.model.TimeScale
import com.example.gymify.home.domain.usecases.WorkoutSessionUseCases
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases,
    private val workoutSessionUseCases: WorkoutSessionUseCases,
    private val timeRangeUtils: TimeRangeUtils,
) : ViewModel() {
    private val _state = MutableStateFlow(AnalyticsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val userHeight = signUpUseCases.readUserHeightUseCase().first()
            val heightInMeters = userHeight / 100
            val userWeight = signUpUseCases.readUserWeightUseCase().first()

            val bmi = userWeight / (heightInMeters * heightInMeters)
            _state.update { it.copy(bmiValue = bmi) }

            onAction(AnalyticsAction.LoadBarData)
        }
    }

    fun onAction(action: AnalyticsAction) {
        when (action) {
            is AnalyticsAction.ChangeTimeScale -> {
                _state.update { it.copy(selectedScale = action.timeScale) }
                onAction(AnalyticsAction.LoadBarData)
            }

            is AnalyticsAction.OnBarClick -> {
                val selectedBar = state.value.data[action.index]
                val selectedDateTimestamp = selectedBar.timestamp

                _state.update {
                    if (state.value.isBarSelected) {
                        it.copy(
                            selectedIndex = null,
                            selectedBarDurationMinutes = null,
                            selectedDateTimestamp = null,
                            isBarSelected = false
                        )
                    } else {
                        it.copy(
                            selectedIndex = action.index,
                            selectedBarDurationMinutes = selectedBar.minutes,
                            selectedDateTimestamp = selectedDateTimestamp,
                            isBarSelected = true
                        )
                    }
                }
            }

            is AnalyticsAction.LoadBarData -> {
                when (state.value.selectedScale) {
                    TimeScale.DAY -> loadDailyBarData()
                    TimeScale.WEEK -> { }
                    TimeScale.MONTH -> { }
                }
            }
        }
    }

    fun loadDailyBarData() {
        viewModelScope.launch {
            val (startOfWeekTimestamp, endOfWeekTimestamp) = timeRangeUtils.getWeekRangeFromTimestamp()

            val currentWeekSessions = workoutSessionUseCases.getSessionsByTimeRangeUseCase(
                startOfWeekTimestamp,
                endOfWeekTimestamp
            )

            val weekBarData = timeRangeUtils.mapSessionsToDayBarData(currentWeekSessions)
            Log.d("Analytics", "mapped bar data: $weekBarData")

            val totalSeconds = currentWeekSessions.sumOf { it.durationSeconds }

            _state.update {
                it.copy(
                    data = weekBarData,
                    totalTimeMinutes = totalSeconds.toInt(),
                )
            }
        }
    }
}