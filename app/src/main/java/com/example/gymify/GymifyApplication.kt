package com.example.gymify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GymifyApplication: Application() {


    override fun onCreate() {
        super.onCreate()

    }



}