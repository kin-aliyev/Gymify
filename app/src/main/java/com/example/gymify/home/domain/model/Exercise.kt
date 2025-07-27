package com.example.gymify.home.domain.model

data class Exercise(
    val id: Int = 0,
    val exerciseNameId: String,
    val muscleGroup: MuscleGroup,
    val firstIconId: String,
    val iconSecondary: String? = null,
    val supportsWeight: Boolean
)
