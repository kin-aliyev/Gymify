package com.example.gymify.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.gymify.core.presentation.navigation.HomeNavigationGraph
import com.example.gymify.home.presentation.exercises_screen.ExerciseViewModel
import com.example.gymify.home.presentation.exercises_screen.ExercisesAction
import com.example.gymify.home.presentation.exercises_screen.ExercisesScreen
import com.example.gymify.home.presentation.home_screen.HomeAction
import com.example.gymify.home.presentation.home_screen.HomeScreen
import com.example.gymify.home.presentation.home_screen.HomeViewModel
import com.example.gymify.home.presentation.make_workoutplan_screen.MakeWorkoutPlanAction
import com.example.gymify.home.presentation.make_workoutplan_screen.MakeWorkoutPlanScreen
import com.example.gymify.home.presentation.make_workoutplan_screen.MakeWorkoutPlanViewModel
import com.example.gymify.home.presentation.muscle_group_screen.MuscleGroupScreen
import com.example.gymify.home.presentation.user_workouts_screen.UserWorkoutsAction
import com.example.gymify.home.presentation.user_workouts_screen.UserWorkoutsScreen
import com.example.gymify.home.presentation.user_workouts_screen.UserWorkoutsViewModel
import com.example.gymify.home.presentation.workout_complete_screen.WorkoutCompleteAction
import com.example.gymify.home.presentation.workout_complete_screen.WorkoutCompleteScreen
import com.example.gymify.home.presentation.workout_complete_screen.WorkoutCompleteViewModel
import com.example.gymify.home.presentation.workout_plan_detail_screen.WorkoutPlanDetailAction
import com.example.gymify.home.presentation.workout_plan_detail_screen.WorkoutPlanDetailScreen
import com.example.gymify.home.presentation.workout_plan_detail_screen.WorkoutPlanDetailViewModel
import com.example.gymify.home.presentation.workout_session_screen.WorkoutSessionAction
import com.example.gymify.home.presentation.workout_session_screen.WorkoutSessionScreen
import com.example.gymify.home.presentation.workout_session_screen.WorkoutSessionViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavController) {

    navigation<HomeNavigationGraph>(startDestination = Home) {
        composable<Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            HomeScreen(
                onNavigate = {
                    navController.navigate(it)
                    {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState =  true
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedDestination = Home,
                state = state,
                onNavigateToAllUserWorkoutPlans = { navController.navigate(UserWorkoutPlans) },
                onAction = { action ->
                    viewModel.onAction(action)

                    when (action) {
                        is HomeAction.OnAddNewWorkoutClick -> {
                            navController.navigate(MakeWorkoutPlan())
                        }

                        is HomeAction.OnWorkoutPlanClick -> {
                            navController.navigate(WorkoutPlanDetail(action.workoutPlan.id))
                        }

                        is HomeAction.OnPredefinedWorkoutPlanClick -> navController.navigate(
                            WorkoutPlanDetail(workoutPlanNameId = action.workoutPlanNameId)
                        )
                    }
                }
            )

        }

        composable<MakeWorkoutPlan> {
            val viewModel: MakeWorkoutPlanViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            MakeWorkoutPlanScreen(
                state = state,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMuscleGroupScreen = { navController.navigate(MuscleGroup) },
                onAction = { action ->
                    viewModel.onAction(action)

                    if (action == MakeWorkoutPlanAction.SaveWorkoutPlan) {
                        navController.navigate(Home) {
                            popUpTo<Home> { inclusive = false }
                        }
                    }

                    if (action == MakeWorkoutPlanAction.ExitWorkoutPlan) {
                        navController.navigate(Home) {
                            popUpTo<Home> { inclusive = false }
                        }
                    }

                }
            )
        }

        composable<MuscleGroup> {
            MuscleGroupScreen(
                onMuscleGroupClick = { navController.navigate(Exercises(it)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable<Exercises> {
            val args = it.toRoute<Exercises>()
            val viewModel: ExerciseViewModel = hiltViewModel()
            val state by viewModel.state

            LaunchedEffect(args.muscleGroup) {
                viewModel.loadExercises(args.muscleGroup)
            }

            ExercisesScreen(
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)

                    if (action is ExercisesAction.OnExerciseClick) {
                        navController.navigate(
                            MakeWorkoutPlan(exerciseId = action.exercise.id)
                        ) {
                            popUpTo<MakeWorkoutPlan> { inclusive = false }
                        }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<UserWorkoutPlans> {
            val viewModel: UserWorkoutsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            UserWorkoutsScreen(
                state = state,
                onAction = { action ->
                    when (action) {
                        is UserWorkoutsAction.NavigateBack -> {
                            navController.popBackStack()
                        }

                        is UserWorkoutsAction.OnAddWorkoutClick -> {
                            navController.navigate(MakeWorkoutPlan())
                        }

                        is UserWorkoutsAction.OnWorkoutPlanClick -> {
                            navController.navigate(WorkoutPlanDetail(action.workoutPlanId))
                        }
                    }
                },
                selectedDestination = UserWorkoutPlans,
                onNavigate = {
                    navController.navigate(it)
                    {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = false
                        }
                        launchSingleTop =
                            true // Избегаем создания дубликатов при повторной навигации
                        restoreState = true  // Восстанавливаем состояние при возврате
                    }
                },
            )
        }

        composable<WorkoutPlanDetail> {
            val viewModel: WorkoutPlanDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            WorkoutPlanDetailScreen(
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)

                    when (action) {
                        is WorkoutPlanDetailAction.OnDeleteWorkoutClick -> {
                            navController.navigate(Home) {
                                popUpTo(Home) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }

                        is WorkoutPlanDetailAction.OnEditWorkoutClick -> {
                            navController.navigate(MakeWorkoutPlan(action.workoutId))
                        }

                        is WorkoutPlanDetailAction.OnStartClick.WithWorkoutId -> {
                            navController.navigate(WorkoutSession(workoutPlanId = action.workoutId))
                        }

                        is WorkoutPlanDetailAction.OnStartClick.WithWorkoutNameId -> {
                            navController.navigate(WorkoutSession(workoutPlanNameId = action.workoutNameId))
                        }

                        is WorkoutPlanDetailAction.OnNavigateBackClick -> {
                            navController.popBackStack()
                        }

                        is WorkoutPlanDetailAction.DeleteWorkoutExercise -> Unit
                        is WorkoutPlanDetailAction.OnEditPredefinedWorkoutClick -> {
                            navController.navigate(MakeWorkoutPlan(workoutPlanNameId = action.workoutPlanNameId))
                        }
                    }
                }
            )
        }

        composable<WorkoutSession> {
            val viewModel: WorkoutSessionViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            WorkoutSessionScreen(
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)

                    if (action is WorkoutSessionAction.FinishWorkout) {
                        navController.navigate(WorkoutComplete(action.seconds))
                    }

                    if (action is WorkoutSessionAction.NavigateBack) {
                        navController.popBackStack()
                    }
                }
            )

        }

        composable<WorkoutComplete> {
            val viewModel: WorkoutCompleteViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            WorkoutCompleteScreen(
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)

                    if (action is WorkoutCompleteAction.OnDoneClick) {
                        navController.navigate(Home) {
                            popUpTo(Home) {
                                inclusive = false
                            }
                        }
                    }
                }
            )
        }
    }
}