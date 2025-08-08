package com.example.gymify.signup.presentation.choose_level_screen

import com.example.gymify.core.domain.model.ExpertiseLevel

data class ChooseLevelState(
    val selectedExpertiseLevel: ExpertiseLevel = ExpertiseLevel.BEGINNER
)