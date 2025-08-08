package com.example.gymify.settings.presentation.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.settings.navigation.Settings
import com.example.gymify.settings.presentation.settings.components.ExerciseRepMax
import com.example.gymify.settings.presentation.settings.components.SettingTab
import com.example.gymify.settings.presentation.settings.components.UserProfilePicture
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { it?.let { uri -> onAction(SettingsAction.OnChangeProfilePictureClick(uri)) } }
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                onNavigate = { destination ->
                    if (selectedDestination != destination) {
                        onNavigate(destination)
                    }
                },
                selectedDestination = selectedDestination,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
        }
    ) {

        Box(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {

            // Upper User Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                Color(0xFF617BB6)
                            )
                        ),
                        shape = RoundedCornerShape(bottomStart = 180.dp, bottomEnd = 180.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    // Settings Line
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(R.drawable.icon_top_app_bar_black),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 24.dp)
                                    .size(50.dp)
                                    .align(Alignment.CenterStart)

                            )

                            Text(
                                text = "Settings",
                                fontFamily = rubikFontFamily,
                                color = Color(0XFFEBEBEB),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)

                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    UserProfilePicture(
                        userName = state.userName,
                        profilePictureUri = state.profilePictureUrl,
                        onEditClick = {
                            imagePickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )
                }


                // Max Weight Circles
                ExerciseRepMax(
                    exerciseName = "Squat",
                    maxWeight = state.squatMaxWeight,
                    userWeightUnit = state.weightUnit,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 25.dp, bottom = 15.dp)
                )

                ExerciseRepMax(
                    exerciseName = "Deadlift",
                    maxWeight = state.deadliftMaxWeight,
                    userWeightUnit = state.weightUnit,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 25.dp, bottom = 15.dp)
                )

                ExerciseRepMax(
                    exerciseName = "Bench",
                    maxWeight = state.benchMaxWeight,
                    userWeightUnit = state.weightUnit,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 35.dp)
                )
            }


            // Setting Tabs

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 12.dp)
                    .padding(top = 130.dp)
            ) {
                SettingTab(
                    settingTabName = stringResource(R.string.user_preferences),
                    onClick = { onAction(SettingsAction.NavigateToUserPreferencesScreen) })
                SettingTab(
                    settingTabName = "App Appearance",
                    onClick = { onAction(SettingsAction.NavigateToAppAppearanceScreen) })
                SettingTab(
                    settingTabName = "Help & Support",
                    onClick = { onAction(SettingsAction.NavigateToHelpAndSupportScreen) })
            }

        }

    }

}

@Preview
@Composable
private fun SettingsScreenPreview() {
    GymifyTheme {
        SettingsScreen(
            state = SettingsState(
                userName = null,
                squatMaxWeight = "150",
                deadliftMaxWeight = "150",
                benchMaxWeight = "105",
                weightUnit = UserWeightUnit.KG
            ),
            onAction = {},
            onNavigate = { },
            selectedDestination = Settings,
        )
    }

}