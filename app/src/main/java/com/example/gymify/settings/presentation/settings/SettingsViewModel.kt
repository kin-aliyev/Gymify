package com.example.gymify.settings.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.settings.domain.usecases.profile_picture.ReadUserProfilePictureUseCase
import com.example.gymify.settings.domain.usecases.profile_picture.SaveUserProfilePictureUseCase
import com.example.gymify.settings.domain.usecases.user_name.ReadUserNameUseCase
import com.example.gymify.settings.util.ImageSaver
import com.example.gymify.signup.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases,
    private val readUserNameUseCase: ReadUserNameUseCase,
    private val readUserProfilePictureUseCase: ReadUserProfilePictureUseCase,
    private val saveUserProfilePictureUseCase: SaveUserProfilePictureUseCase,
    private val imageSaver: ImageSaver,
) : ViewModel() {
    val state = combine(
        readUserNameUseCase(),
        readUserProfilePictureUseCase(),
        signUpUseCases.readUserWeightUnitUseCase(),
        // Добавьте другие Flow для maxWeight данных
    ) { userName, profilePictureUrl, weightUnit ->
        SettingsState(
            userName = userName,
            profilePictureUrl = profilePictureUrl,
            weightUnit = weightUnit,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SettingsState()
    )



    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnChangeProfilePictureClick -> {
                viewModelScope.launch {
                    val savedPath = imageSaver.saveProfilePictureCropped(action.uri)
                    savedPath?.let { saveUserProfilePictureUseCase(savedPath) }
                }
            }

            SettingsAction.NavigateToAppAppearanceScreen -> Unit
            SettingsAction.NavigateToHelpAndSupportScreen -> Unit
            SettingsAction.NavigateToUserPreferencesScreen -> Unit
        }
    }
}