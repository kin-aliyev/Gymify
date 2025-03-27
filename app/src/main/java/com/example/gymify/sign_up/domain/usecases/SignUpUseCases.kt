package com.example.gymify.sign_up.domain.usecases

import com.example.gymify.sign_up.domain.usecases.gender.ReadUserGenderUseCase
import com.example.gymify.sign_up.domain.usecases.gender.SaveUserGenderUseCase
import com.example.gymify.sign_up.domain.usecases.height.ReadUserHeightUnitUseCase
import com.example.gymify.sign_up.domain.usecases.height.ReadUserHeightUseCase
import com.example.gymify.sign_up.domain.usecases.height.SaveUserHeightUnitUseCase
import com.example.gymify.sign_up.domain.usecases.height.SaveUserHeightUseCase
import com.example.gymify.sign_up.domain.usecases.level.ReadUserExpertiseLevelUseCase
import com.example.gymify.sign_up.domain.usecases.level.SaveUserExpertiseLevelUseCase
import com.example.gymify.sign_up.domain.usecases.weight.ReadUserWeightUnitUseCase
import com.example.gymify.sign_up.domain.usecases.weight.ReadUserWeightUseCase
import com.example.gymify.sign_up.domain.usecases.weight.SaveUserWeightUnitUseCase
import com.example.gymify.sign_up.domain.usecases.weight.SaveUserWeightUseCase

data class SignUpUseCases (
    val saveUserGenderUseCase: SaveUserGenderUseCase,
    val readUserGenderUseCase: ReadUserGenderUseCase,

    val saveUserHeightUseCase: SaveUserHeightUseCase,
    val readUserHeightUseCase: ReadUserHeightUseCase,

    val saveUserHeightUnitUseCase: SaveUserHeightUnitUseCase,
    val readUserHeightUnitUseCase: ReadUserHeightUnitUseCase,

    val saveUserWeightUseCase: SaveUserWeightUseCase,
    val readUserWeightUseCase: ReadUserWeightUseCase,

    val saveUserWeightUnitUseCase: SaveUserWeightUnitUseCase,
    val readUserWeightUnitUseCase: ReadUserWeightUnitUseCase,

    val saveUserExpertiseLevelUseCase: SaveUserExpertiseLevelUseCase,
    val readUserExpertiseLevelUseCase: ReadUserExpertiseLevelUseCase,
)