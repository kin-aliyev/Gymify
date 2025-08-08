package com.example.gymify.settings.domain.usecases.profile_picture

import com.example.gymify.core.domain.manager.LocalUserInfoManager

class SaveUserProfilePictureUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    suspend operator fun invoke(uri: String) {
        localUserInfoManager.saveUserProfilePicture(uri)
    }
}