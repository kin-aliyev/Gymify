package com.example.gymify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.gymify.core.data.manager.LocaleManager
import com.example.gymify.core.domain.model.ThemeMode
import com.example.gymify.core.presentation.navigation.MainNavigationGraph
import com.example.gymify.ui.theme.GymifyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var localeManager: LocaleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Observe for language changes that require activity recreation
        lifecycleScope.launch {
            localeManager.recreateActivities.collect { shouldRecreate ->
                if (shouldRecreate) {
                    localeManager.resetRecreationFlag()
                    recreate() // Recreate the activity when language changes
                }
            }
        }

        enableEdgeToEdge()

        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val state by viewModel.state

            splashscreen.setKeepOnScreenCondition {
                state.isLoading
            }

            val themeMode by viewModel.themeMode.collectAsState(initial = ThemeMode.SYSTEM)


            val isDarkTheme = when (themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            GymifyTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                if (!state.isLoading) {
                    MainNavigationGraph(navController, isRegistered = state.isRegistered)
                } else {
                    // Пока данные загружаются, можно показать пустой контент
                    // Splash screen будет поверх него
                    Log.d("mainsoso", "Still loading, not showing navigation yet")
                }
            }
        }
    }
}

