package com.example.gymify.signup.di

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.signup.domain.usecases.SignUpUseCases
import com.example.gymify.signup.domain.usecases.gender.ReadUserGenderUseCase
import com.example.gymify.signup.domain.usecases.gender.SaveUserGenderUseCase
import com.example.gymify.signup.domain.usecases.height.ReadUserHeightUnitUseCase
import com.example.gymify.signup.domain.usecases.height.ReadUserHeightUseCase
import com.example.gymify.signup.domain.usecases.height.SaveUserHeightUnitUseCase
import com.example.gymify.signup.domain.usecases.height.SaveUserHeightUseCase
import com.example.gymify.signup.domain.usecases.level.ReadUserExpertiseLevelUseCase
import com.example.gymify.signup.domain.usecases.level.SaveUserExpertiseLevelUseCase
import com.example.gymify.signup.domain.usecases.weight.ReadUserWeightUnitUseCase
import com.example.gymify.signup.domain.usecases.weight.ReadUserWeightUseCase
import com.example.gymify.signup.domain.usecases.weight.SaveUserWeightUnitUseCase
import com.example.gymify.signup.domain.usecases.weight.SaveUserWeightUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        localUserInfoManager: LocalUserInfoManager
    ): SignUpUseCases = SignUpUseCases(
        saveUserGenderUseCase = SaveUserGenderUseCase(localUserInfoManager),
        readUserGenderUseCase = ReadUserGenderUseCase(localUserInfoManager),

        saveUserHeightUseCase = SaveUserHeightUseCase(localUserInfoManager),
        readUserHeightUseCase = ReadUserHeightUseCase(localUserInfoManager),

        saveUserHeightUnitUseCase = SaveUserHeightUnitUseCase(localUserInfoManager),
        readUserHeightUnitUseCase = ReadUserHeightUnitUseCase(localUserInfoManager),

        saveUserWeightUseCase = SaveUserWeightUseCase(localUserInfoManager),
        readUserWeightUseCase = ReadUserWeightUseCase(localUserInfoManager),

        saveUserWeightUnitUseCase = SaveUserWeightUnitUseCase(localUserInfoManager),
        readUserWeightUnitUseCase = ReadUserWeightUnitUseCase(localUserInfoManager),

        saveUserExpertiseLevelUseCase = SaveUserExpertiseLevelUseCase(localUserInfoManager),
        readUserExpertiseLevelUseCase = ReadUserExpertiseLevelUseCase(localUserInfoManager)
    )
}