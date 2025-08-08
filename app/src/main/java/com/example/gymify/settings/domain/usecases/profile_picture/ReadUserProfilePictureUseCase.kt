package com.example.gymify.settings.domain.usecases.profile_picture

import com.example.gymify.core.domain.manager.LocalUserInfoManager
import kotlinx.coroutines.flow.Flow

class ReadUserProfilePictureUseCase(
    private val localUserInfoManager: LocalUserInfoManager
) {
    operator fun invoke(): Flow<String?> {
        return localUserInfoManager.readUserProfilePicture()
    }
}