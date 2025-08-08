package com.example.gymify.settings.presentation.userpreferences

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.settings.navigation.EditProfile
import com.example.gymify.core.util.formatHeight
import com.example.gymify.core.util.formatWeight
import com.example.gymify.core.util.getLocalizedName
import com.example.gymify.settings.presentation.userpreferences.components.ChangeOptionBottomSheetTab
import com.example.gymify.settings.presentation.userpreferences.components.ChangeNumericSettingTab
import com.example.gymify.settings.presentation.userpreferences.components.ChangeOptionTab
import com.example.gymify.settings.presentation.userpreferences.components.ChangeUserNameTab
import com.example.gymify.settings.presentation.userpreferences.components.ChangeUserPhoto
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPreferencesScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
    state: UserPreferencesState,
    onAction: (UserPreferencesAction) -> Unit,
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            BackTopBar(
                title = "User Preferences",
                onBackIconClick = { onAction(UserPreferencesAction.NavigateBack) },
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .verticalScroll(scrollState)
                .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
                .padding(horizontal = 12.dp)
        ) {
            ChangeUserPhoto(
                onChangePhotoClick = { onAction(UserPreferencesAction.GetNewProfilePictureUri(it)) },
                profilePictureUri = state.profilePictureUri
            )

            Spacer(Modifier.height(32.dp))

            ChangeUserNameTab(
                userName = state.userName ?: "Gym Bro #1",
                onUserNameChanged = { onAction(UserPreferencesAction.OnChangeUserName(it)) },
            )

            Spacer(Modifier.height(16.dp))

            ChangeOptionBottomSheetTab(
                selectedOption = state.userGender,
                settingName = "Gender",
                onOptionChange = { onAction(UserPreferencesAction.OnChangeUserGender(it)) },
                options = UserGender.entries,
                optionToString = { it.getLocalizedName(context) }
            )

            Spacer(Modifier.height(16.dp))

            ChangeNumericSettingTab(
                settingName = "Age",
                settingNumericValue = state.userAge?.toString() ?: "0",
                settingValueString = if (state.userAge != null) {
                    "${state.userAge} years"
                } else "Not Selected",
                onChangeUserValue = { onAction(UserPreferencesAction.OnChangeUserAge(it.toInt())) }
            )

            Spacer(Modifier.height(16.dp))

            ChangeNumericSettingTab(
                settingName = "Height",
                settingNumericValue = state.userHeight?.toString() ?: "0",
                settingValueString = if (state.userHeight != null) {
                    "${formatHeight(state.userHeight.toString(), state.userHeightUnit)} " +
                            state.userHeightUnit.getLocalizedName(context)
                } else "Not Selected",
                onChangeUserValue = { onAction(UserPreferencesAction.OnChangeUserHeight(it)) }
            )

            Spacer(Modifier.height(16.dp))

            ChangeOptionTab(
                settingName = "Height Unit",
                settingValue = state.userHeightUnit.getLocalizedName(context),
                options = UserHeightUnit.entries,
                optionToString = { it.getLocalizedName(context) },
                onOptionClick = { onAction(UserPreferencesAction.OnChangeUserHeightUnit(it))}
            )

            Spacer(Modifier.height(16.dp))

            ChangeNumericSettingTab(
                settingName = "Weight",
                settingNumericValue = state.userWeight?.toString() ?: "0",
                settingValueString = if (state.userWeight != null) {
                    "${formatWeight(state.userWeight.toString(), state.userWeightUnit)} " +
                            state.userWeightUnit.getLocalizedName(context)
                } else "Not selected",
                onChangeUserValue = { onAction(UserPreferencesAction.OnChangeUserWeight(it)) }
            )

            Spacer(Modifier.height(16.dp))

            ChangeOptionTab(
                settingName = "Weight Unit",
                settingValue = state.userWeightUnit.getLocalizedName(context),
                options = UserWeightUnit.entries,
                optionToString = { it.getLocalizedName(context) },
                onOptionClick = { onAction(UserPreferencesAction.OnChangeUserWeightUnit(it)) }
            )

        }
    }
}

@Preview
@Composable
private fun EditProfileScreenPreview() {
    GymifyTheme {
        UserPreferencesScreen(
            onAction = {},
            onNavigate = {},
            selectedDestination = EditProfile,
            state = UserPreferencesState(
                userHeight = 180f,
                userHeightUnit = UserHeightUnit.CM,
                userAge = 28,
                userWeight = 82f,
                userWeightUnit = UserWeightUnit.LBS
            )
        )
    }
}
