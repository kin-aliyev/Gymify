package com.example.gymify.settings.di

import android.content.Context
import com.example.gymify.core.domain.manager.LocalUserInfoManager
import com.example.gymify.settings.domain.usecases.profile_picture.ReadUserProfilePictureUseCase
import com.example.gymify.settings.domain.usecases.profile_picture.SaveUserProfilePictureUseCase
import com.example.gymify.settings.domain.usecases.user_age.ReadUserAgeUseCase
import com.example.gymify.settings.domain.usecases.user_age.SaveUserAgeUseCase
import com.example.gymify.settings.domain.usecases.user_name.ReadUserNameUseCase
import com.example.gymify.settings.domain.usecases.user_name.SaveUserNameUseCase
import com.example.gymify.settings.util.ImageSaver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    // Save/Read User Age Use Cases
    @Singleton
    @Provides
    fun provideReadUserAgeUseCase(
        localUserInfoManager: LocalUserInfoManager
    ): ReadUserAgeUseCase = ReadUserAgeUseCase(localUserInfoManager)

    @Singleton
    @Provides
    fun provideSaveUserAgeUseCase(
        localUserInfoManager: LocalUserInfoManager
    ): SaveUserAgeUseCase = SaveUserAgeUseCase(localUserInfoManager)



    // Save/Read Profile Picture Use Cases
    @Singleton
    @Provides
    fun provideReadUserProfilePictureUseCase(
        localUserInfoManager: LocalUserInfoManager,
    ): ReadUserProfilePictureUseCase = ReadUserProfilePictureUseCase(localUserInfoManager)
    @Singleton
    @Provides
    fun provideSaveUserProfilePictureUseCase(
        localUserInfoManager: LocalUserInfoManager
    ): SaveUserProfilePictureUseCase = SaveUserProfilePictureUseCase(localUserInfoManager)



    // Save/Read User Name Use Cases
    @Singleton
    @Provides
    fun provideReadUserNameUseCase(
        localUserInfoManager: LocalUserInfoManager
    ): ReadUserNameUseCase = ReadUserNameUseCase(localUserInfoManager)

    @Singleton
    @Provides
    fun provideSaveUserNameUseCase(
        localUserInfoManager: LocalUserInfoManager
    ): SaveUserNameUseCase = SaveUserNameUseCase(localUserInfoManager)

}