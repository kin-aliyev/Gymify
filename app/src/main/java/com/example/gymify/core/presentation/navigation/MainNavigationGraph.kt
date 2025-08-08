package com.example.gymify.core.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.gymify.analytics.navigation.analyticsNavGraph
import com.example.gymify.home.navigation.homeNavGraph
import com.example.gymify.settings.navigation.settingsNavGraph
import com.example.gymify.signup.navigation.signUpNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph( navController: NavHostController, isRegistered: Boolean ) {
    NavHost(
        navController = navController,
        startDestination = if (isRegistered) HomeNavigationGraph else SignUpNavigationGraph,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {
        signUpNavGraph(navController)
        homeNavGraph(navController)
        analyticsNavGraph(navController)
        settingsNavGraph(navController)
    }
}