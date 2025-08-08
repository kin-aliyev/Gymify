package com.example.gymify.signup.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.gymify.core.presentation.navigation.SignUpNavigationGraph
import com.example.gymify.home.navigation.Home
import com.example.gymify.signup.presentation.choose_level_screen.ChooseLevelScreen
import com.example.gymify.signup.presentation.choose_level_screen.ChooseLevelViewModel
import com.example.gymify.signup.presentation.pick_gender_screen.PickGenderScreen
import com.example.gymify.signup.presentation.pick_gender_screen.PickGenderViewModel
import com.example.gymify.signup.presentation.pick_height_screen.PickHeightScreen
import com.example.gymify.signup.presentation.pick_height_screen.PickHeightViewModel
import com.example.gymify.signup.presentation.pick_weight_screen.PickWeightScreen
import com.example.gymify.signup.presentation.pick_weight_screen.PickWeightViewModel
import com.example.gymify.signup.presentation.welcome_screen.WelcomeScreen

fun NavGraphBuilder.signUpNavGraph(navController: NavController) {
    navigation<SignUpNavigationGraph>(startDestination = Welcome) {

        composable<Welcome> {
            WelcomeScreen(onNavigateToGenderScreen = { navController.navigate(Gender) })
        }

        composable<Gender> {
            val viewModel: PickGenderViewModel = hiltViewModel()
            val state = viewModel.state.value
            PickGenderScreen(
                userGender = state.selectedGender,
                onAction = viewModel::onAction,
                onNavigateToHeightScreen = { navController.navigate(Height) }
            )
        }

        composable<Height> {
            val viewModel: PickHeightViewModel = hiltViewModel()
            val state = viewModel.state.value
            PickHeightScreen(
                userHeightUnit = state.selectedHeightUnit,
                onAction = viewModel::onAction,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToWeightScreen = { navController.navigate(Weight) }
            )
        }

        composable<Weight> {
            val viewModel: PickWeightViewModel = hiltViewModel()
            val state = viewModel.state.value
            PickWeightScreen(
                userWeightUnit = state.selectedWeightUnit,
                onAction = viewModel::onAction,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToUserLevel = { navController.navigate(Level) }
            )
        }

        composable<Level> {
            val viewModel: ChooseLevelViewModel = hiltViewModel()
            val state = viewModel.state.value
            ChooseLevelScreen(
                expertiseLevel = state.selectedExpertiseLevel,
                onAction = viewModel::onAction,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMainModule = {
                    navController.navigate(Home) {
                        popUpTo(Welcome) { inclusive = true }
                    }
                }
            )
        }
    }
}
