package com.example.gymify.settings.presentation.userpreferences

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.settings.domain.usecases.profile_picture.ReadUserProfilePictureUseCase
import com.example.gymify.settings.domain.usecases.profile_picture.SaveUserProfilePictureUseCase
import com.example.gymify.settings.domain.usecases.user_age.ReadUserAgeUseCase
import com.example.gymify.settings.domain.usecases.user_age.SaveUserAgeUseCase
import com.example.gymify.settings.domain.usecases.user_name.ReadUserNameUseCase
import com.example.gymify.settings.domain.usecases.user_name.SaveUserNameUseCase
import com.example.gymify.settings.util.ImageSaver
import com.example.gymify.signup.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val imageSaver: ImageSaver,
    private val signUpUpUseCases: SignUpUseCases,
    readUserNameUseCase: ReadUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    readUserProfilePictureUseCase: ReadUserProfilePictureUseCase,
    private val saveUserProfilePictureUseCase: SaveUserProfilePictureUseCase,
    readUserAgeUseCase: ReadUserAgeUseCase,
    private val saveUserAgeUseCase: SaveUserAgeUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val userBasicInfo = combine(
        readUserProfilePictureUseCase(),
        readUserNameUseCase(),
        readUserAgeUseCase(),
        signUpUpUseCases.readUserGenderUseCase()
    ) { profilePictureUri, userName, userAge, userGender ->
        BasicUserInfo(profilePictureUri, userName, userAge, userGender)
    }

    private val userPhysicalInfo = combine(
        signUpUpUseCases.readUserHeightUseCase(),
        signUpUpUseCases.readUserHeightUnitUseCase(),
        signUpUpUseCases.readUserWeightUseCase(),
        signUpUpUseCases.readUserWeightUnitUseCase()
    ) { userHeight, userHeightUnit, userWeight, userWeightUnit ->
        PhysicalUserInfo(userHeight, userHeightUnit, userWeight, userWeightUnit)
    }

    val state = combine(
        userBasicInfo,
        userPhysicalInfo
    ) { basicInfo, physicalInfo ->
        UserPreferencesState(
            profilePictureUri = basicInfo.profilePictureUri,
            userName = basicInfo.userName,
            userAge = basicInfo.userAge,
            userGender = basicInfo.userGender,
            userHeight = physicalInfo.userHeight,
            userHeightUnit = physicalInfo.userHeightUnit,
            userWeight = physicalInfo.userWeight,
            userWeightUnit = physicalInfo.userWeightUnit
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = UserPreferencesState()
    )

    fun onAction(action: UserPreferencesAction) {
        when (action) {
            is UserPreferencesAction.GetNewProfilePictureUri -> {
                viewModelScope.launch {
                    val savedPath = imageSaver.saveProfilePictureCropped(action.uri)
                    savedPath?.let { saveUserProfilePictureUseCase(it) }
                }
            }

            is UserPreferencesAction.OnChangeUserName -> {
                viewModelScope.launch {
                    saveUserNameUseCase(action.name)
                }
            }

            is UserPreferencesAction.OnChangeUserGender -> {
                viewModelScope.launch {
                    signUpUpUseCases.saveUserGenderUseCase(action.userGender)
                }
            }

            is UserPreferencesAction.OnChangeUserAge -> {
                viewModelScope.launch {
                    saveUserAgeUseCase(action.userAge)
                }
            }

            is UserPreferencesAction.OnChangeUserHeight -> {
                viewModelScope.launch {
                    signUpUpUseCases.saveUserHeightUseCase(action.userHeight)
                }
            }

            is UserPreferencesAction.OnChangeUserHeightUnit -> {
                viewModelScope.launch {
                    signUpUpUseCases.saveUserHeightUnitUseCase(action.userHeightUnit)
                }
            }

            is UserPreferencesAction.OnChangeUserWeight -> {
                viewModelScope.launch {
                    signUpUpUseCases.saveUserWeightUseCase(action.userWeight)
                }
            }

            is UserPreferencesAction.OnChangeUserWeightUnit -> {
                viewModelScope.launch {
                    signUpUpUseCases.saveUserWeightUnitUseCase(action.userWeightUnit)
                }
            }

            is UserPreferencesAction.NavigateBack -> Unit
        }
    }

}

private data class BasicUserInfo(
    val profilePictureUri: String?,
    val userName: String?,
    val userAge: Int?,
    val userGender: UserGender
)

private data class PhysicalUserInfo(
    val userHeight: Float?,
    val userHeightUnit: UserHeightUnit,
    val userWeight: Float?,
    val userWeightUnit: UserWeightUnit
)