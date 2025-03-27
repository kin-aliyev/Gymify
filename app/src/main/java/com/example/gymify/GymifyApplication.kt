package com.example.gymify

import android.app.Application
import android.content.Context
import com.example.gymify.core.domain.usecases.language.GetLanguageUseCase
import com.example.gymify.core.util.LocaleHelper
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltAndroidApp
class GymifyApplication: Application() {

    @Inject
    lateinit var getLanguageUseCase: GetLanguageUseCase

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    // Переопределяем attachBaseContext для обновления конфигурации
//    override fun attachBaseContext(base: Context?) {
//        if (base == null) {
//            super.attachBaseContext(null)
//            return
//        }
//
//        super.attachBaseContext(localeUpdatedContext)
//    }

    override fun onCreate() {
        super.onCreate()

        // Инициализируем язык в onCreate, когда доступны зависимости Hilt
        applicationScope.launch {
            updateApplicationLanguage()
        }

    }

    // Метод для обновления языка приложения
    private suspend fun updateApplicationLanguage() {
        try {
            val languageCode = getLanguageUseCase()
            LocaleHelper.setLocale(applicationContext, languageCode)
        } catch (e: Exception) {
            // Обработка ошибок при получении/установке языка
        }
    }
    
}