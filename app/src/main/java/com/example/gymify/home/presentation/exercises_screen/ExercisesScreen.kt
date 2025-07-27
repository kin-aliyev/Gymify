package com.example.gymify.home.presentation.exercises_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.R
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.home.util.ExerciseIconMapper
import com.example.gymify.home.util.ExerciseNameMapper
import com.example.gymify.home.util.MuscleGroupNameMapper
import com.example.gymify.home.domain.model.Exercise
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.presentation.components.ExerciseGridItem
import com.example.gymify.home.presentation.exercises_screen.components.ExerciseSearchBar
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onAction: (ExercisesAction) -> Unit,
    state: ExercisesState,
) {
    val muscleGroupName = state.selectedMuscleGroup?.let {
        stringResource(MuscleGroupNameMapper.getName(it.nameKey))
    } ?: stringResource(R.string.no_muscle_group_selected)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Показываем отфильтрованные упражнения если есть поиск
    val exercisesToShow = if (state.searchText.isNotEmpty()) {
        state.filteredExercises
    } else {
        state.exercises
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BackTopBar(
                title = stringResource(
                    R.string.group,
                    muscleGroupName
                ),
                scrollBehavior = scrollBehavior,
                onBackIconClick = onNavigateBack,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding() + 8.dp)
        ) {
            ExerciseSearchBar(
                onClearClick = { onAction(ExercisesAction.OnClearSearch) },
                searchText = state.searchText,
                onSearchTextChange = { onAction(ExercisesAction.OnSearchTextChange(it)) },
                modifier = Modifier.padding(horizontal = 16.dp)
                    .padding(bottom = 4.dp)
            )
            LazyVerticalGrid(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                itemsIndexed(exercisesToShow) { index, exercise ->
                    val backgroundShape = index % 2 == 0
                    ExerciseGridItem(
                        exerciseName = ExerciseNameMapper.getName(exercise.exerciseNameId),
                        iconFirst = ExerciseIconMapper.getIcon(exercise.firstIconId),
                        iconSecond = exercise.iconSecondary?.let { ExerciseIconMapper.getIcon(it )},
                        onClick = { 
                            onAction(ExercisesAction.OnExerciseClick(exercise))
                        },
                        backgroundShape = backgroundShape
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewExercisesScreen() {
    GymifyTheme {
        ExercisesScreen(
            onNavigateBack = {},
            onAction = { },
            state = ExercisesState(
                selectedMuscleGroup = MuscleGroup.LEGS,
                exercises = listOf(
                    Exercise(
                        id = 1,
                        exerciseNameId = "legs_barbell_squat",
                        muscleGroup = MuscleGroup.LEGS,
                        firstIconId = "ic_legs_barbell_squat_start",
                        iconSecondary = "ic_legs_barbell_squat_end",
                        supportsWeight = true
                    ),
                    Exercise(
                        id = 2,
                        exerciseNameId = "legs_leg_press",
                        muscleGroup = MuscleGroup.LEGS,
                        firstIconId = "ic_legs_leg_press_start",
                        iconSecondary = "ic_legs_leg_press_end",
                        supportsWeight = true
                    )
                )
            )
        )
    }
}