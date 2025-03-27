package com.example.gymify.sign_up.di

import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.sign_up.domain.usecases.SignUpUseCases
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