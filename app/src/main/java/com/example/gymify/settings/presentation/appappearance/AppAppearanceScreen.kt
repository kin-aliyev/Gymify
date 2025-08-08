package com.example.gymify.settings.presentation.appappearance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.domain.model.ThemeMode
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.settings.navigation.AppAppearance
import com.example.gymify.core.util.getLocalizedName
import com.example.gymify.settings.presentation.components.SettingTabNavigate
import com.example.gymify.settings.presentation.userpreferences.components.ChangeOptionBottomSheetTab
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAppearanceScreen(
    modifier: Modifier = Modifier,
    selectedDestination: NavigationDestination,
    onNavigate: (NavigationDestination) -> Unit,
    onAction: (AppAppearanceAction) -> Unit,
    state: AppAppearanceState,
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            BackTopBar(
                title = "App Appearance",
                onBackIconClick = { onAction(AppAppearanceAction.OnNavigateBack) },
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            Spacer(Modifier.height(10.dp))

            ChangeOptionBottomSheetTab(
                settingName = "Theme",
                selectedOption = state.themeMode,
                options = ThemeMode.entries,
                onOptionChange = { onAction(AppAppearanceAction.OnThemeModeChange(it)) },
                optionToString = { it.getLocalizedName(context) }
            )

            Spacer(Modifier.height(16.dp))

            SettingTabNavigate(
                settingName = "Language",
                settingValue = state.selectedLanguage.languageName,
                onNavigateToSettingClick = { onAction(AppAppearanceAction.OnNavigateToLanguageSettings) }
            )
        }
    }
}

@Preview
@Composable
private fun AppAppearanceScreenPreview() {
    GymifyTheme {
        AppAppearanceScreen(
            onAction = { },
            onNavigate = { },
            selectedDestination = AppAppearance,
            state = AppAppearanceState()
        )
    }
}