package com.example.gymify.settings.presentation.language

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.settings.navigation.Language
import com.example.gymify.settings.presentation.language.components.LanguageTab
import com.example.gymify.settings.presentation.language.components.AppLanguage
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier,
    onAction: (LanguageAction) -> Unit,
    selectedDestination: NavigationDestination,
    onNavigate: (NavigationDestination) -> Unit,
    state: LanguageState
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier,
        topBar = {
            BackTopBar(
                title = "Language",
                onBackIconClick = { onAction(LanguageAction.OnNavigateBack) },
                scrollBehavior = scrollBehavior,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 12.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = selectedDestination,
                onNavigate = { destination ->
                    if (selectedDestination != destination) {
                        onNavigate(destination)
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 16.dp)
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

            LanguageTab(
                icon = AppLanguage.ENGLISH.icon,
                isSelected = state.selectedLanguage == AppLanguage.ENGLISH,
                languageName = AppLanguage.ENGLISH.languageName,
                onClick = { onAction(LanguageAction.OnChangeLanguage(AppLanguage.ENGLISH))}
            )

            LanguageTab(
                icon = AppLanguage.RUSSIAN.icon,
                isSelected = state.selectedLanguage == AppLanguage.RUSSIAN,
                languageName = AppLanguage.RUSSIAN.languageName,
                onClick = { onAction(LanguageAction.OnChangeLanguage(AppLanguage.RUSSIAN))}
            )

            LanguageTab(
                icon = AppLanguage.AZERBAIJANI.icon,
                isSelected = state.selectedLanguage == AppLanguage.AZERBAIJANI,
                languageName = AppLanguage.AZERBAIJANI.languageName,
                onClick = { onAction(LanguageAction.OnChangeLanguage(AppLanguage.AZERBAIJANI))}
            )
        }
    }
}

@Preview
@Composable
private fun LanguageScreenPreview() {
    GymifyTheme {
        LanguageScreen(
            onAction = { },
            selectedDestination = Language,
            onNavigate = { },
            state = LanguageState()
        )
    }
}
