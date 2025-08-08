package com.example.gymify.core.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gymify.core.domain.manager.AppCoreManager
import com.example.gymify.core.domain.model.ThemeMode
import com.example.gymify.core.util.AppPreferenceKeys
import com.example.gymify.core.util.DataStoreNames
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.appPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreNames.APP)

class AppCoreManagerImpl(
    private val context: Context,
) : AppCoreManager {

    override suspend fun saveRegistrationStatus(isRegistered: Boolean) {
        context.appPreferencesDataStore.edit { appPreferences ->
            appPreferences[CorePreferencesKeys.REGISTRATION] = isRegistered
        }
    }

    override fun readRegistrationStatus(): Flow<Boolean> {
        return context.appPreferencesDataStore.data.map { appPreferences ->
            appPreferences[CorePreferencesKeys.REGISTRATION] ?: false
        }
    }

    override suspend fun setThemeMode(themeMode: ThemeMode) {
        context.appPreferencesDataStore.edit { appPreferences ->
            appPreferences[CorePreferencesKeys.THEME_MODE] = themeMode.ordinal
        }
    }

    override val themeModeFlow: Flow<ThemeMode> = context.appPreferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("ThemeRepository", "Error reading preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { appPreferences ->
            val themeOrdinal = appPreferences[CorePreferencesKeys.THEME_MODE] ?: ThemeMode.SYSTEM.ordinal
            ThemeMode.fromOrdinal(themeOrdinal)
        }


    override suspend fun setLanguage(language: String) {
        context.appPreferencesDataStore.edit { appPreferences ->
            appPreferences[CorePreferencesKeys.LANGUAGE_KEY] = language
        }
    }

    override val languageFlow: Flow<String> = context.appPreferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("LanguageRepository", "Error reading language preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { appPreferences ->
            appPreferences[CorePreferencesKeys.LANGUAGE_KEY] ?: "en"
        }
}

private object CorePreferencesKeys {
    val REGISTRATION = booleanPreferencesKey(name = AppPreferenceKeys.REGISTRATION)
    val THEME_MODE = intPreferencesKey(name = AppPreferenceKeys.THEME_MODE)
    val LANGUAGE_KEY = stringPreferencesKey(name = AppPreferenceKeys.LANGUAGE_KEY)
}