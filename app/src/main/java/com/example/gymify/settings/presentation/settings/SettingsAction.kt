package com.example.gymify.settings.presentation.settings

import android.net.Uri

sealed interface SettingsAction {
    // Profile actions
    data class OnChangeProfilePictureClick(val uri: Uri) : SettingsAction

    // Navigate actions
    object NavigateToUserPreferencesScreen : SettingsAction
    object NavigateToAppAppearanceScreen : SettingsAction
    object NavigateToHelpAndSupportScreen : SettingsAction

}