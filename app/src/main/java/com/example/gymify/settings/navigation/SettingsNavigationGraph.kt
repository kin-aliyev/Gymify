package com.example.gymify.settings.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymify.core.presentation.navigation.SettingsNavigationGraph
import com.example.gymify.settings.presentation.appappearance.AppAppearanceAction
import com.example.gymify.settings.presentation.appappearance.AppAppearanceScreen
import com.example.gymify.settings.presentation.appappearance.AppAppearanceViewModel
import com.example.gymify.settings.presentation.helpandsupport.HelpAndSupportAction
import com.example.gymify.settings.presentation.helpandsupport.HelpAndSupportScreen
import com.example.gymify.settings.presentation.language.LanguageAction
import com.example.gymify.settings.presentation.language.LanguageScreen
import com.example.gymify.settings.presentation.language.LanguageViewModel
import com.example.gymify.settings.presentation.userpreferences.UserPreferencesAction
import com.example.gymify.settings.presentation.userpreferences.UserPreferencesScreen
import com.example.gymify.settings.presentation.userpreferences.UserPreferencesViewModel
import com.example.gymify.settings.presentation.settings.SettingsAction
import com.example.gymify.settings.presentation.settings.SettingsScreen
import com.example.gymify.settings.presentation.settings.SettingsViewModel

fun NavGraphBuilder.settingsNavGraph(navController: NavController) {

    navigation<SettingsNavigationGraph>(startDestination = Settings) {
        composable<Settings> {
            val viewModel: SettingsViewModel = hiltViewModel()
            val state = viewModel.state.value

            SettingsScreen(
                onNavigate = {
                    navController.navigate(it)
                    {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedDestination = Settings,
                onAction = { action ->
                    when (action) {
                        is SettingsAction.OnChangeProfilePictureClick -> viewModel.onAction(action)
                        SettingsAction.NavigateToUserPreferencesScreen -> {
                            navController.navigate(UserPreferences)
                        }

                        SettingsAction.NavigateToAppAppearanceScreen -> {
                            navController.navigate(AppAppearance)
                        }

                        SettingsAction.NavigateToHelpAndSupportScreen -> {
                            navController.navigate(HelpAndSupport)
                        }
                    }
                },
                state = state,
            )
        }

        composable<UserPreferences> {
            val viewModel: UserPreferencesViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            UserPreferencesScreen(
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                selectedDestination = UserPreferences,
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)

                    if (action is UserPreferencesAction.NavigateBack) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable<AppAppearance> {
            val viewmodel: AppAppearanceViewModel = hiltViewModel()
            val state by viewmodel.state.collectAsState()

            AppAppearanceScreen(
                selectedDestination = AppAppearance,
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo(Settings) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                state = state,
                onAction = { action ->
                    viewmodel.onAction(action)

                    when (action) {
                        AppAppearanceAction.OnNavigateBack -> {
                            navController.popBackStack()
                        }

                        AppAppearanceAction.OnNavigateToLanguageSettings -> {
                            navController.navigate(Language)
                        }

                        is AppAppearanceAction.OnThemeModeChange -> Unit
                    }

                },

                )
        }

        composable<Language> {
            val viewModel: LanguageViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            LanguageScreen(
                selectedDestination = Language,
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo(Settings) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)

                    if (action == LanguageAction.OnNavigateBack) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable<HelpAndSupport> {
            HelpAndSupportScreen(
                selectedDestination = HelpAndSupport,
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo(Settings) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onAction = { action ->
                    if (action is HelpAndSupportAction.NavigateBack) {
                        navController.popBackStack()
                    }
                }
            )
        }

    }
}