package com.example.gymify.settings.presentation.app_appearance_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.core.presentation.navigation.settings.AppAppearance
import com.example.gymify.core.util.getLocalizedName
import com.example.gymify.settings.presentation.app_appearance_screen.components.ThemeSelectionBottomSheet
import com.example.gymify.settings.presentation.user_preferences_screen.components.PreferenceSettingTab
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAppearanceScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    appLanguage: String,
    onNavigateToLanguageScreen: () -> Unit,
    selectedDestination: NavigationDestination,
    onNavigate: (NavigationDestination) -> Unit,
    onAction: (AppAppearanceAction) -> Unit,
    state: AppAppearanceState
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier,
        topBar = {
            BackTopBar(
                title = "App Appearance",
                onBackIconClick = onNavigateBack,
                scrollBehavior = scrollBehavior,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                selectedDestination = selectedDestination,
                onNavigate = { onNavigate(it) }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(horizontal = 12.dp)
        ) {
            Spacer(Modifier.height(10.dp))

            PreferenceSettingTab(
                settingTabName = "Theme",
                value = state.themeMode.getLocalizedName(context),
                onClick = { onAction(AppAppearanceAction.OpenThemeBottomSheet) }
            )

            PreferenceSettingTab(
                settingTabName = "Language",
                value = appLanguage,
                onClick = onNavigateToLanguageScreen
            )
        }

        ThemeSelectionBottomSheet(
            show = true,
            selectedTheme = state.temporaryThemeMode ?: state.themeMode,
            onThemeSelected = { theme ->
                onAction(AppAppearanceAction.ThemeModeSelected(theme))
            },
            onConfirm = {
                onAction(AppAppearanceAction.ConfirmThemeSelection)
            },
            onDismiss = {
                onAction(AppAppearanceAction.CancelThemeSelection)
            }
        )
    }
}

@Preview
@Composable
private fun AppAppearanceScreenPreview() {
    GymifyTheme {
        AppAppearanceScreen(
            onNavigateBack = { },
            onAction = { },
            appLanguage = "English(US)",
            onNavigate = { },
            selectedDestination = AppAppearance,
            state = AppAppearanceState(isThemeBottomSheetOpen = false),
            onNavigateToLanguageScreen = { }
        )
    }
}