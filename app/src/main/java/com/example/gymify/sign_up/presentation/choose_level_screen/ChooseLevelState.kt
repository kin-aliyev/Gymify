package com.example.gymify.sign_up.presentation.choose_level_screen

import com.example.gymify.core.domain.model.ExpertiseLevel

data class ChooseLevelState(
    val selectedExpertiseLevel: ExpertiseLevel = ExpertiseLevel.BEGINNER
)