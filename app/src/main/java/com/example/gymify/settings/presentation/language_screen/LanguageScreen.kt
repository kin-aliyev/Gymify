package com.example.gymify.settings.presentation.language_screen

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
import com.example.gymify.core.presentation.navigation.settings.Language
import com.example.gymify.settings.presentation.language_screen.components.LanguageTab
import com.example.gymify.settings.presentation.language_screen.components.LanguageTabItem
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
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
                onBackIconClick = onNavigateBack,
                scrollBehavior = scrollBehavior,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 6.dp)
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
                icon = LanguageTabItem.English.icon,
                isSelected = state.selectedLanguage == LanguageTabItem.English,
                languageName = LanguageTabItem.English.languageName,
                onClick = { onAction(LanguageAction.LanguageSelected(LanguageTabItem.English))}
            )

            LanguageTab(
                icon = LanguageTabItem.Russian.icon,
                isSelected = state.selectedLanguage == LanguageTabItem.Russian,
                languageName = LanguageTabItem.Russian.languageName,
                onClick = { onAction(LanguageAction.LanguageSelected(LanguageTabItem.Russian))}
            )

            LanguageTab(
                icon = LanguageTabItem.Azerbaijan.icon,
                isSelected = state.selectedLanguage == LanguageTabItem.Azerbaijan,
                languageName = LanguageTabItem.Azerbaijan.languageName,
                onClick = { onAction(LanguageAction.LanguageSelected(LanguageTabItem.Azerbaijan))}
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
            onNavigateBack = { },
            selectedDestination = Language,
            onNavigate = { },
            state = LanguageState()
        )
    }
}
