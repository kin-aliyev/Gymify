package com.example.gymify.sign_up.presentation.choose_level_screen

import com.example.gymify.core.domain.model.ExpertiseLevel

sealed interface ChooseLevelAction {
    data class onExpertiseLevelClick(val expertiseLevel: ExpertiseLevel): ChooseLevelAction
    object SaveExpertiseLevel: ChooseLevelAction

}