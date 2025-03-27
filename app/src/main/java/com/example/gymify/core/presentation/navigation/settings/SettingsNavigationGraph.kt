package com.example.gymify.core.presentation.navigation.settings

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymify.core.presentation.navigation.SettingsNavigationGraph
import com.example.gymify.settings.presentation.app_appearance_screen.AppAppearanceScreen
import com.example.gymify.settings.presentation.app_appearance_screen.AppAppearanceViewModel
import com.example.gymify.settings.presentation.settings_screen.SettingsScreen
import com.example.gymify.settings.presentation.settings_screen.SettingsViewModel
import com.example.gymify.settings.presentation.user_preferences_screen.UserPreferencesScreen

fun NavGraphBuilder.settingsNavGraph(navController: NavController) {
    navigation<SettingsNavigationGraph>(startDestination = Settings) {

        composable<Settings> {
            val viewModel: SettingsViewModel = hiltViewModel()
            val state = viewModel.state.value
            SettingsScreen(
                selectedDestination = Settings,
                onNavigate = { navController.navigate(it) {
                        launchSingleTop = true // Избегаем создания дубликатов при повторной навигации
                        restoreState = true  // Восстанавливаем состояние при возврате
                    }
                },
                onAction = viewModel::onAction,
                state = state,
                onNavigateToAppAppearance = { navController.navigate(AppAppearance) },
                onNavigateToHelpAndSupport = { navController.navigate(HelpAndSupport) },
                onNavigateToUserPreferences = { navController.navigate(UserPreferences) }
            )
        }

        composable<UserPreferences> {
//            UserPreferencesScreen(
//                selectedDestination = UserPreferences,
//                onNavigate = { navController.navigate(it) {
//                    // Удаляем все до корневого экрана
//                    popUpTo(Settings) {
//                        saveState = true
//                        inclusive = false
//                    }
//                    launchSingleTop = true // Избегаем создания дубликатов при повторной навигации
//                    restoreState = true  // Восстанавливаем состояние при возврате
//                } },
//                onNavigateBack = { navController.navigate(Settings) },
//            )
        }

        composable<AppAppearance> {
//            val viewmodel: AppAppearanceViewModel = hiltViewModel()
//            val state = viewmodel.state.value
//            AppAppearanceScreen(
//                selectedDestination = AppAppearance,
//                onNavigate = { navController.navigate(it) {
//                    // Удаляем все до корневого экрана
//                    popUpTo(Settings) {
//                        saveState = true
//                        inclusive = false
//                    }
//                    launchSingleTop = true // Избегаем создания дубликатов при повторной навигации
//                    restoreState = true  // Восстанавливаем состояние при возврате
//                } },
//                onNavigateToLanguageScreen = { navController.navigate(Language) },
//                onNavigateBack = { navController.navigate(Settings) },
//                state = state,
//                onAction = viewmodel::onAction,
//
//            )
        }

    }
}