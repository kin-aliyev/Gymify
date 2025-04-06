package com.example.gymify.main.presentation.make_workoutplan_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.model.MuscleGroup
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo
import com.example.gymify.main.presentation.components.AddButton
import com.example.gymify.main.presentation.components.AddedExerciseView
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.main.presentation.make_workoutplan_screen.components.OptionTabChoose
import com.example.gymify.main.presentation.make_workoutplan_screen.components.OptionTabName
import com.example.gymify.main.presentation.make_workoutplan_screen.components.OptionTabSets
import com.example.gymify.main.presentation.make_workoutplan_screen.components.OptionTabWeight
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.util.MuscleGroupNameMapper
import com.example.gymify.main.domain.model.WorkoutExercise
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeWorkoutPlanScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    state: MakeWorkoutPlanState,
    onPlanNameChanged: (String) -> Unit,
    onMuscleGroupClick: () -> Unit,
    onNumOfSetsChanged: (String) -> Unit,
    onNumOfRepsChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onAddExercise: () -> Unit,
    onSaveWorkoutPlan: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val muscleGroupNameResId = state.selectedMuscleGroup?.let {
        MuscleGroupNameMapper.getName(it.nameKey)
    } ?: R.string.no_muscle_group_selected

    Scaffold(
        modifier = modifier,
        topBar = {
            BackTopBar(
                title = stringResource(R.string.title_your_workout_plans),
                scrollBehavior = scrollBehavior,
                onBackIconClick = onNavigateBack,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Spacer(Modifier.height(10.dp))

            OptionTabSets(
                optionName = stringResource(R.string.tab_name),
                numOfSets = state.planName,
                onNumOfSetsChanged = { onPlanNameChanged(it) },
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(16.dp))

            OptionTabName(
                optionName = "The Exercise",
                exerciseValue = state.exerciseNameKey ?: "",
                modifier = Modifier.padding(horizontal = 12.dp),
            )

            Spacer(Modifier.height(16.dp))

            OptionTabChoose(
                optionName = stringResource(R.string.tab_muscle_group),
                muscleGroupName = stringResource(muscleGroupNameResId),
                onMuscleGroupClick = onMuscleGroupClick,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(16.dp))

            OptionTabSets(
                optionName = stringResource(R.string.tab_number_of_sets),
                numOfSets = state.numberOfSets,
                onNumOfSetsChanged = { onNumOfSetsChanged(it) },
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(16.dp))

            OptionTabSets(
                optionName = stringResource(R.string.tab_number_of_reps),
                numOfSets = state.numberOfReps,
                onNumOfSetsChanged = { onNumOfRepsChanged(it) },
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(16.dp))

            OptionTabWeight(
                optionName = stringResource(R.string.tab_working_weight),
                weight = state.weight,
                onWeightChange = { onWeightChanged(it) },
                userWeightUnit = state.userWeightUnit,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (state.selectedExercises.toList().isEmpty()) Arrangement.Center
                else Arrangement.SpaceEvenly
            ) {
                AddButton(
                    onClick = onAddExercise,
                    buttonText = "Add",
                    isAddButton = true
                )

                if (state.selectedExercises.values.toList().isNotEmpty()) {
                    AddButton(
                        onClick = onSaveWorkoutPlan,
                        buttonText = "Save",
                        isAddButton = false
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "List of Added Exercises",
                fontSize = 22.sp,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFF617BB6)
                        )
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )

            Spacer(Modifier.height(16.dp))

            HorizontalDivider(
                color = Color(0xFF3B4051),
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.selectedExercises.values.toList()) { workoutExerciseRelation ->
                    AddedExerciseView(
                        workoutExerciseWithExerciseInfo = workoutExerciseRelation
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MakeWorkoutPlanScreenPreview() {
    GymifyTheme {
        MakeWorkoutPlanScreen(
            onNavigateBack = { },
            onMuscleGroupClick = { },
            onNumOfSetsChanged = { },
            onNumOfRepsChanged = { },
            onWeightChanged = { },
            onSaveWorkoutPlan = { },
            onAddExercise = { },
            onPlanNameChanged = { },
            state = MakeWorkoutPlanState(),


//            workoutExerciseWithExerciseInfos = listOf(
//                WorkoutExerciseWithExerciseInfo(
//                    workoutExercise = WorkoutExercise(
//                        id = 0,
//                        weight = 10f,
//                        sets = 1,
//                        reps = 1,
//                        workoutPlanId = 2,
//                        exerciseId = 0
//                    ),
//                    exercise = Exercise(
//                        0, "", MuscleGroup.BACK,
//                        "", "", true,
//                    ),
//
//                    )
//            ),
        )
    }
}





