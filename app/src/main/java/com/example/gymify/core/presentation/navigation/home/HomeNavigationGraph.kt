package com.example.gymify.core.presentation.navigation.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.gymify.core.presentation.navigation.HomeNavigationGraph
import com.example.gymify.main.presentation.home_screen.HomeScreen
import com.example.gymify.sign_up.presentation.welcome_screen.WelcomeScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation<HomeNavigationGraph>(startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onNavigate = { navController.navigate(it)
                {
//                   // Удаляем все до корневого экрана
//                    popUpTo(Home) {
//                        saveState = true
//                        inclusive = false
//                    }
                    launchSingleTop = true // Избегаем создания дубликатов при повторной навигации
                    restoreState = true  // Восстанавливаем состояние при возврате
                }
                             },
                selectedDestination = Home
            )
        }
    }
}
