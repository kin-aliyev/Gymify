package com.example.gymify.core.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gymify.core.util.DataStoreNames
import com.example.gymify.core.util.UserInfoPreferenceKeys
import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreNames.USER_INFO)

class LocalUserInfoManagerImpl(
    private val context: Context,
) : LocalUserInfoManager {

    // User Age pref
    override suspend fun saveUserAge(userAge: Int) {
        context.userPreferencesDataStore.edit {userPreferences ->
            userPreferences[UserProfilePreferencesKeys.USER_AGE] = userAge
        }
    }

    override fun readUserAge(): Flow<Int?> {
        return context.userPreferencesDataStore.data.map { userPreferences ->
            userPreferences[UserProfilePreferencesKeys.USER_AGE]
        }
    }

    // User Gender pref
    override suspend fun saveUserGender(userGender: UserGender) {
        context.userPreferencesDataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.GENDER] = userGender.genderId
        }
    }

    override fun readUserGender(): Flow<UserGender> {
        return context.userPreferencesDataStore.data.map { userInfo ->
            val genderId = userInfo[SignUpPreferencesKeys.GENDER] ?: UserGender.MALE.genderId
            UserGender.entries.first { it.genderId == genderId }
        }
    }

    // User Height pref
    override suspend fun saveUserHeight(userHeight: Float) {
        context.userPreferencesDataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.HEIGHT] = userHeight
        }
    }

    override fun readUserHeight(): Flow<Float> {
        return context.userPreferencesDataStore.data.map { userInfo ->
            userInfo[SignUpPreferencesKeys.HEIGHT] ?: 170f
        }
    }

    // User HeightUnit pref
    override suspend fun saveUserHeightUnit(userHeightUnit: UserHeightUnit) {
        context.userPreferencesDataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.HEIGHT_UNIT] = userHeightUnit.unitId
        }
    }

    override fun readUserHeightUnit(): Flow<UserHeightUnit> {
        return context.userPreferencesDataStore.data.map { userInfo ->
            val unitId = userInfo[SignUpPreferencesKeys.HEIGHT_UNIT] ?: UserHeightUnit.CM.unitId
            UserHeightUnit.entries.first{ it.unitId == unitId}
        }
    }

    // User Weight pref
    override suspend fun saveUserWeight(userWeight: Float) {
        context.userPreferencesDataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.WEIGHT] = userWeight
        }

    }

    override fun readUserWeight(): Flow<Float> {
        return context.userPreferencesDataStore.data.map { userInfo ->
            userInfo[SignUpPreferencesKeys.WEIGHT] ?: 60f
        }
    }

    // User WeightUnit pref
    override suspend fun saveUserWeightUnit(userWeightUnit: UserWeightUnit) {
        context.userPreferencesDataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.WEIGHT_UNIT] = userWeightUnit.unitId
        }
    }

    override fun readUserWeightUnit(): Flow<UserWeightUnit> {
        return context.userPreferencesDataStore.data.map { userInfo ->
            val unitId = userInfo[SignUpPreferencesKeys.WEIGHT_UNIT]  ?: UserWeightUnit.KG.unitId
            UserWeightUnit.entries.first { it.unitId == unitId }
        }
    }

    // User ExpertiseLevel pref
    override suspend fun saveUserExpertiseLevel(expertiseLevel: ExpertiseLevel) {
        context.userPreferencesDataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.EXPERTISE_LEVEL] = expertiseLevel.expertiseId
        }
    }

    override fun readUserExpertiseLevel(): Flow<ExpertiseLevel> {
        return context.userPreferencesDataStore.data.map { userInfo ->
            val expertiseId = userInfo[SignUpPreferencesKeys.EXPERTISE_LEVEL] ?: ExpertiseLevel.BEGINNER.expertiseId
            ExpertiseLevel.entries.first { it.expertiseId == expertiseId}
        }
    }

    override suspend fun saveUserProfilePicture(uri: String) {
        context.userPreferencesDataStore.edit { userPreferences ->
            userPreferences[UserProfilePreferencesKeys.PROFILE_PICTURE_URI] = uri
        }
    }

    override fun readUserProfilePicture(): Flow<String?> {
        return context.userPreferencesDataStore.data.map { userPreferences ->
            userPreferences[UserProfilePreferencesKeys.PROFILE_PICTURE_URI]
        }
    }

    override suspend fun saveUserName(name: String) {
        context.userPreferencesDataStore.edit { userPreferences ->
            userPreferences[UserProfilePreferencesKeys.USER_NAME] = name
        }
    }

    override fun readUserName(): Flow<String?> {
        return context.userPreferencesDataStore.data.map { userPreferences ->
            userPreferences[UserProfilePreferencesKeys.USER_NAME]
        }
    }
}

private object UserProfilePreferencesKeys {
    val USER_AGE = intPreferencesKey(name = UserInfoPreferenceKeys.AGE)
    val USER_NAME = stringPreferencesKey(name = UserInfoPreferenceKeys.NAME)
    val PROFILE_PICTURE_URI = stringPreferencesKey(name = UserInfoPreferenceKeys.PROFILE_PICTURE_URI)
}

private object SignUpPreferencesKeys {
    val GENDER = intPreferencesKey(name = UserInfoPreferenceKeys.GENDER)
    val HEIGHT = floatPreferencesKey(name = UserInfoPreferenceKeys.HEIGHT)
    val HEIGHT_UNIT = intPreferencesKey(name = UserInfoPreferenceKeys.HEIGHT_UNIT)
    val WEIGHT = floatPreferencesKey(name = UserInfoPreferenceKeys.WEIGHT)
    val WEIGHT_UNIT = intPreferencesKey(name = UserInfoPreferenceKeys.WEIGHT_UNIT)
    val EXPERTISE_LEVEL = intPreferencesKey(name = UserInfoPreferenceKeys.EXPERTISE_LEVEL)
}