@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymify.settings.presentation.user_preferences_screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.core.presentation.navigation.settings.UserPreferences
import com.example.gymify.core.util.formatHeight
import com.example.gymify.core.util.formatWeight
import com.example.gymify.core.util.getLocalizedName
import com.example.gymify.settings.presentation.user_preferences_screen.components.PreferenceSettingTab
import com.example.gymify.sign_up.domain.model.UserGender
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun UserPreferencesScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
    onNavigateBack: () -> Unit,
    state: UserPreferencesState,
    onAction: (UserPreferencesAction) -> Unit
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier,
        topBar = {
            BackTopBar(
                title = stringResource(R.string.user_preferences),
                onBackIconClick = onNavigateBack,
                scrollBehavior = scrollBehavior,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigate = { onNavigate(it) },
                selectedDestination = selectedDestination,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
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

            PreferenceSettingTab( settingTabName = "Gender", value = state.userGender.getLocalizedName(context),
                onClick = { onAction(UserPreferencesAction.ShowGenderSelectionDialog) })

            PreferenceSettingTab( settingTabName = "Age", value = state.userAge.toString(),
                onClick = { onAction(UserPreferencesAction.ShowAgeSelectionDialog) })

            val formattedHeight = formatHeight(state.userHeight, state.userHeightUnit)
            PreferenceSettingTab( settingTabName = "Height", value = "$formattedHeight ${state.userHeightUnit.getLocalizedName(context)}",
                onClick = { onAction(UserPreferencesAction.ShowHeightSelectionDialog) })

            val formattedWeight = formatWeight(state.userWeight, state.userWeightUnit)
            PreferenceSettingTab( settingTabName = "Weight", value = "$formattedWeight ${state.userWeightUnit.getLocalizedName(context)}",
                onClick = { onAction(UserPreferencesAction.ShowWeightSelectionDialog) })

            PreferenceSettingTab( settingTabName = "WeightUnit", value = state.userWeightUnit.getLocalizedName(context),
                onClick = { onAction(UserPreferencesAction.ShowWeightUnitSelectionDialog) })

            PreferenceSettingTab( settingTabName = "HeightUnit", value = state.userHeightUnit.getLocalizedName(context),
                onClick = { onAction(UserPreferencesAction.ShowHeightUnitSelectionDialog) })

        }
    }
}

@Preview
@Composable
private fun UserPreferencesScreenPreview() {
    GymifyTheme {
        val previewState = UserPreferencesState(
            userGender = UserGender.MALE,
            userAge = 28,
            userHeight = "185",
            userWeight = "75",
            userWeightUnit = UserWeightUnit.KG,
            userHeightUnit = UserHeightUnit.CM,
        )
        UserPreferencesScreen(
            state = previewState,
            onAction = { },
            onNavigateBack = { },
            onNavigate = { },
            selectedDestination = UserPreferences
        )
    }

}