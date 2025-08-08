package com.example.gymify.core.domain.manager

import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.domain.model.UserGender
import kotlinx.coroutines.flow.Flow

interface LocalUserInfoManager {

    // Save and Read User Age
    suspend fun saveUserAge(userAge: Int)
    fun readUserAge(): Flow<Int?>

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

    // Save and Read User Profile Picture Uri
    suspend fun saveUserProfilePicture(uri: String)
    fun readUserProfilePicture(): Flow<String?>

    // Save and Read User Name
    suspend fun saveUserName(name: String)
    fun readUserName(): Flow<String?>
}