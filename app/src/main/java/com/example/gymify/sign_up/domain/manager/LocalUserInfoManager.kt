package com.example.gymify.sign_up.domain.manager

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.sign_up.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import kotlinx.coroutines.flow.Flow

interface LocalUserInfoManager {

    // Save and Read User Gender
    suspend fun saveUserGender(userGender: UserGender)
    fun readUserGender(): Flow<UserGender>

    // Save and Read User Height
    suspend fun saveUserHeight(userHeight: Float)
    fun readUserHeight(): Flow<Float>

    // Save and Read User Height Unit
    suspend fun saveUserHeightUnit(userHeightUnit: UserHeightUnit)
    fun readUserHeightUnit(): Flow<UserHeightUnit>

    // Save and Read User Weight
    suspend fun saveUserWeight(userWeight: Float)
    fun readUserWeight(): Flow<Float>

    // Save and Read User Weight Unit
    suspend fun saveUserWeightUnit(userWeightUnit: UserWeightUnit)
    fun readUserWeightUnit(): Flow<UserWeightUnit>

    // Save and Read User Expertise Level
    suspend fun saveUserExpertiseLevel(expertiseLevel: ExpertiseLevel)
    fun readUserExpertiseLevel(): Flow<ExpertiseLevel>

}