package com.example.gymify.core.presentation.navigation.analytics

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymify.core.presentation.navigation.AnalyticsNavigationGraph
import com.example.gymify.home.presentation.analytics_screen.AnalyticsScreen
import com.example.gymify.home.presentation.analytics_screen.AnalyticsViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.analyticsNavGraph(navController: NavController) {
    navigation<AnalyticsNavigationGraph>(startDestination = Analytics) {
        composable<Analytics> {
            val viewModel: AnalyticsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            AnalyticsScreen(
                state = state,
                onAction = viewModel::onAction,
                onNavigate = {
                    navController.navigate(it)
                    {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = false
                        }
                        launchSingleTop = true // Избегаем создания дубликатов при повторной навигации
                        restoreState = true  // Восстанавливаем состояние при возврате
                    }
                },
                selectedDestination = Analytics,
            )
        }
    }
}