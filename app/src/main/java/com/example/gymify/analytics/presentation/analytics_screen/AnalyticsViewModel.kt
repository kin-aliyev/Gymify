package com.example.gymify.analytics.presentation.analytics_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.home.util.TimeRangeUtils
import com.example.gymify.home.domain.model.TimeScale
import com.example.gymify.home.domain.usecases.WorkoutSessionUseCases
import com.example.gymify.home.util.TimeConverter
import com.example.gymify.signup.domain.usecases.SignUpUseCases
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
                _state.update {
                    it.copy(
                        selectedScale = action.timeScale,
                        // Сбрасываем выделение при смене типа графика
                        selectedIndex = null,
                        selectedBarDurationMinutes = null,
                        selectedDateTitle = null,
                        isBarSelected = false,
                    )
                }
                onAction(AnalyticsAction.LoadBarData)
            }

            is AnalyticsAction.OnBarClick -> {
                val selectedBar = state.value.data[action.index]
                val selectedDateTimestamp = selectedBar.timestamp

                val dateTitle = getDateTitleForSelectedBar(action.index, selectedDateTimestamp)

                _state.update {
                    if (state.value.isBarSelected) {
                        it.copy(
                            selectedIndex = null,
                            selectedBarDurationMinutes = null,
                            selectedDateTitle = null,
                            isBarSelected = false
                        )
                    } else {
                        it.copy(
                            selectedIndex = action.index,
                            selectedBarDurationMinutes = selectedBar.minutes,
                            selectedDateTitle = dateTitle,
                            isBarSelected = true
                        )
                    }
                }
            }

            is AnalyticsAction.LoadBarData -> {
                when (state.value.selectedScale) {
                    TimeScale.DAY -> loadDailyBarData()
                    TimeScale.WEEK -> loadWeeklyBarData()
                    TimeScale.MONTH -> loadMonthlyBarData()
                }
            }
        }
    }

    private fun loadDailyBarData() {
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
                    totalTimeMinutes = (totalSeconds / 60).toInt(),
                )
            }
        }
    }

    private fun loadWeeklyBarData() {
        viewModelScope.launch {
            val (start, end) = timeRangeUtils.getFourWeeksRangeFromTimestamp()
            val currentMonthSessions = workoutSessionUseCases.getSessionsByTimeRangeUseCase(start, end)

            val monthBarData = timeRangeUtils.mapSessionsToWeekBarData(currentMonthSessions)

            val totalSeconds = currentMonthSessions.sumOf { it.durationSeconds }

            _state.update {
                it.copy(
                    data = monthBarData,
                    totalTimeMinutes = (totalSeconds / 60).toInt()
                )
            }
        }
    }

    private fun loadMonthlyBarData() {
        viewModelScope.launch {
            val (start, end) = timeRangeUtils.getYearRangeFromTimestamp()
            val currentYearSessions = workoutSessionUseCases.getSessionsByTimeRangeUseCase(start, end)

            val yearBarData = timeRangeUtils.mapSessionsToMonthBarData(currentYearSessions)

            val totalSeconds = currentYearSessions.sumOf { it.durationSeconds }

            _state.update {
                it.copy(
                    data = yearBarData,
                    totalTimeMinutes = (totalSeconds / 60).toInt()
                )
            }
        }
    }

    private fun getDateTitleForSelectedBar(index: Int, timeStamp: Long?): String? {
        return when (state.value.selectedScale) {
            TimeScale.DAY -> {
                timeStamp?.let {
                    TimeConverter().convertLongToDate(
                        timeStamp = it,
                        pattern = TimeConverter.Patterns.WEEKDAY_ANALYTICS_DATE,
                    ).replaceFirstChar { char -> char.titlecase() }
                }
            }

            TimeScale.WEEK -> { timeRangeUtils.getWeekHeaderTitle(index) }

            TimeScale.MONTH -> {
                timeStamp?.let {
                    TimeConverter().convertLongToDate(
                        timeStamp = it,
                        pattern = TimeConverter.Patterns.MONTH_DATE
                    ).replaceFirstChar { char -> char.titlecase() }
                }
            }
        }
    }
}