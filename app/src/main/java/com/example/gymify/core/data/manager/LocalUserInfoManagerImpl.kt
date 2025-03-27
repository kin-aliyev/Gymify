package com.example.gymify.core.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gymify.core.util.DataStoreNames
import com.example.gymify.core.util.UserInfoPreferenceKeys
import com.example.gymify.sign_up.domain.manager.LocalUserInfoManager
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.sign_up.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreNames.USER_INFO)

class LocalUserInfoManagerImpl(
    private val context: Context,
) : LocalUserInfoManager {

    // Pick Gender Screen Actions
    override suspend fun saveUserGender(userGender: UserGender) {
        context.dataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.GENDER] = userGender.genderId
        }

    }

    override fun readUserGender(): Flow<UserGender> {
        return context.dataStore.data.map { userInfo ->
            val genderId = userInfo[SignUpPreferencesKeys.GENDER] ?: UserGender.MALE.genderId
            UserGender.entries.first { it.genderId == genderId }
        }
    }

    // Pick Height Screen Actions
    override suspend fun saveUserHeight(userHeight: Float) {
        context.dataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.HEIGHT] = userHeight
        }

        Log.d("DataStore", "User Weight saved: $userHeight")
    }

    override fun readUserHeight(): Flow<Float> {
        return context.dataStore.data.map { userInfo ->
            userInfo[SignUpPreferencesKeys.HEIGHT] ?: 170f
        }
    }

    override suspend fun saveUserHeightUnit(userHeightUnit: UserHeightUnit) {
        context.dataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.HEIGHT_UNIT] = userHeightUnit.unitId
        }

        Log.d("DataStore", "User Weight Unit saved: ${userHeightUnit.unitId} (${userHeightUnit.name})")
    }

    override fun readUserHeightUnit(): Flow<UserHeightUnit> {
        return context.dataStore.data.map { userInfo ->
            val unitId = userInfo[SignUpPreferencesKeys.HEIGHT_UNIT] ?: UserHeightUnit.CM.unitId
            UserHeightUnit.entries.first{ it.unitId == unitId}
        }
    }

    override suspend fun saveUserWeight(userWeight: Float) {
        context.dataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.WEIGHT] = userWeight
        }

        Log.d("DataStore", "User Weight saved: $userWeight")
    }

    override fun readUserWeight(): Flow<Float> {
        return context.dataStore.data.map { userInfo ->
            userInfo[SignUpPreferencesKeys.WEIGHT] ?: 60f
        }
    }

    override suspend fun saveUserWeightUnit(userWeightUnit: UserWeightUnit) {
        context.dataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.WEIGHT_UNIT] = userWeightUnit.unitId
        }

        Log.d("DataStore", "User Weight Unit saved: ${userWeightUnit.unitId} (${userWeightUnit.name})")
    }

    override fun readUserWeightUnit(): Flow<UserWeightUnit> {
        return context.dataStore.data.map { userInfo ->
            val unitId = userInfo[SignUpPreferencesKeys.WEIGHT_UNIT]  ?: UserWeightUnit.KG.unitId
            UserWeightUnit.entries.first() {it.unitId == unitId}
        }
    }

    override suspend fun saveUserExpertiseLevel(expertiseLevel: ExpertiseLevel) {
        context.dataStore.edit { userInfo ->
            userInfo[SignUpPreferencesKeys.EXPERTISE_LEVEL] = expertiseLevel.expertiseId
        }
    }

    override fun readUserExpertiseLevel(): Flow<ExpertiseLevel> {
        return context.dataStore.data.map { userInfo ->
            val expertiseId = userInfo[SignUpPreferencesKeys.EXPERTISE_LEVEL] ?: ExpertiseLevel.BEGINNER.expertiseId
            ExpertiseLevel.entries.first { it.expertiseId == expertiseId}
        }
    }
}

private object SignUpPreferencesKeys {
    val GENDER = intPreferencesKey(name = UserInfoPreferenceKeys.GENDER)
    val HEIGHT = floatPreferencesKey(name = UserInfoPreferenceKeys.HEIGHT)
    val HEIGHT_UNIT = intPreferencesKey(name = UserInfoPreferenceKeys.HEIGHT_UNIT)
    val WEIGHT = floatPreferencesKey(name = UserInfoPreferenceKeys.WEIGHT)
    val WEIGHT_UNIT = intPreferencesKey(name = UserInfoPreferenceKeys.WEIGHT_UNIT)
    val EXPERTISE_LEVEL = intPreferencesKey(name = UserInfoPreferenceKeys.EXPERTISE_LEVEL)
}