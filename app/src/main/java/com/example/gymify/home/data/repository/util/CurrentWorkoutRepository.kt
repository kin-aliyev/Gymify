package com.example.gymify.home.data.repository.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWorkoutRepository @Inject constructor() {
    private var currentWorkoutId: Int? = null

    fun getCurrentWorkoutId(): Int? = currentWorkoutId
    fun setCurrentWorkoutId(id: Int?) {
        currentWorkoutId = id
    }
}