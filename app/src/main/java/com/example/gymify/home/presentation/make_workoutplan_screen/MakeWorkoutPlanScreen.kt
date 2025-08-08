package com.example.gymify.home.presentation.make_workoutplan_screen

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.home.presentation.components.AddButton
import com.example.gymify.home.presentation.components.AddedExerciseView
import com.example.gymify.home.presentation.components.CustomAlertDialog
import com.example.gymify.home.presentation.make_workoutplan_screen.components.OptionTabChoose
import com.example.gymify.home.presentation.make_workoutplan_screen.components.OptionTabName
import com.example.gymify.home.presentation.make_workoutplan_screen.components.OptionTabSets
import com.example.gymify.home.presentation.make_workoutplan_screen.components.OptionTabWeight
import com.example.gymify.home.presentation.make_workoutplan_screen.components.UploadPhotoCard
import com.example.gymify.home.util.WorkoutPlanNameMapper
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeWorkoutPlanScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateToMuscleGroupScreen: () -> Unit,
    state: MakeWorkoutPlanState,
    onAction: (MakeWorkoutPlanAction) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { it?.let { uri -> onAction(MakeWorkoutPlanAction.OnImageSelected(uri)) } }
    )

    var showAlertDialog by remember { mutableStateOf(false) }

    BackHandler {
        showAlertDialog = true
    }

    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            BackTopBar(
                title = stringResource(R.string.title_make_workout_plan),
                scrollBehavior = scrollBehavior,
                onBackIconClick = { showAlertDialog = true },
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            item { Spacer(Modifier.height(10.dp)) }

            item {
                UploadPhotoCard(
                    imagePath = state.workoutPlanIconUri,
                    planIconId = state.displayIconId,
                    onUploadImageClick = {
                        imagePickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

            item {
                Spacer(Modifier.height(16.dp))
            }

            item {
                OptionTabSets(
                    optionName = stringResource(R.string.tab_plan_name),
                    inputValue = state.workoutPlanName
                        ?: state.workoutPlanNameId?.let {
                            stringResource(WorkoutPlanNameMapper.getName(it))
                        } ?: "",
                    onInputValueChanged = {
                        onAction(
                            MakeWorkoutPlanAction.OnWorkoutPlanNameChanged(
                                it
                            )
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp),
                    isForPlanName = true,
                    isReadOnly = state.isPredefinedPlan
                )
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                OptionTabChoose(
                    optionName = stringResource(R.string.tab_muscle_group),
                    muscleGroupName = state.selectedMuscleGroupName,
                    onMuscleGroupClick = onNavigateToMuscleGroupScreen,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                OptionTabName(
                    optionName = "Exercise",
                    exerciseValue = state.selectedExerciseName,
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                OptionTabSets(
                    optionName = stringResource(R.string.tab_number_of_sets),
                    inputValue = state.numberOfSets,
                    onInputValueChanged = { onAction(MakeWorkoutPlanAction.OnSetsChanged(it)) },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                OptionTabSets(
                    optionName = stringResource(R.string.tab_number_of_reps),
                    inputValue = state.numberOfReps,
                    onInputValueChanged = { onAction(MakeWorkoutPlanAction.OnRepsChanged(it)) },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

            state.selectedExercise?.supportsWeight.let {
                item { Spacer(Modifier.height(12.dp)) }

                item {
                    OptionTabWeight(
                        optionName = stringResource(R.string.tab_working_weight),
                        weight = state.weight,
                        onWeightChange = { onAction(MakeWorkoutPlanAction.OnWeightChanged(it)) },
                        userWeightUnit = state.userWeightUnit,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }



            item { Spacer(Modifier.height(18.dp)) }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = if (state.selectedExercises.isEmpty()) Arrangement.Center else Arrangement.SpaceEvenly
                ) {
                    AddButton(
                        onClick = { onAction(MakeWorkoutPlanAction.AddExercise) },
                        buttonText = if (state.isEditingMode) "Update" else "Add",
                        isAddButton = true
                    )

                    if (state.selectedExercises.isNotEmpty()) {
                        AddButton(
                            onClick = if (state.isEditingMode) {
                                { onAction(MakeWorkoutPlanAction.CancelEdit) }
                            } else {
                                { onAction(MakeWorkoutPlanAction.SaveWorkoutPlan) }
                            },
                            buttonText = if (state.isEditingMode) "Cancel" else "Save",
                            isAddButton = false
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(32.dp)) }

            item {
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
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item { Spacer(Modifier.height(16.dp)) }

            item {
                HorizontalDivider(
                    color = Color(0xFF3B4051),
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }

            if (state.selectedExercises.isNotEmpty()) {

                item { Spacer(Modifier.height(16.dp)) }


                itemsIndexed(state.selectedExercises) { index, workoutExerciseRelation ->
                    AddedExerciseView(
                        workoutExerciseWithExerciseInfo = workoutExerciseRelation,
                        onEditClick = { onAction(MakeWorkoutPlanAction.EditExercise(it)) },
                        onDeleteClick = { onAction(MakeWorkoutPlanAction.DeleteExercise(it)) },
                        userWeightUnit = state.userWeightUnit
                    )

                    if (index != state.selectedExercises.lastIndex) {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            } else {

                item { Spacer(Modifier.height(22.dp)) }

                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_empty_folder),
                            contentDescription = null,
                            tint = Color(0xFFEBEBEB),
                            modifier = Modifier.size(90.dp)
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(6.dp)) }

                item {
                    Text(
                        text = "You haven't added anything yet",
                        fontSize = 14.sp,
                        color = Color(0xFFEBEBEB).copy(0.65f),
                        textAlign = TextAlign.Center,
                        fontFamily = rubikFontFamily,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(22.dp))
                }
            }
        }

        if (showAlertDialog) {
            CustomAlertDialog(
                onDismissRequest = { showAlertDialog = false },
                onConfirmation = {
                    onNavigateBack()
                    showAlertDialog = false
                    onAction(MakeWorkoutPlanAction.SaveWorkoutPlan)
                },
                title = "Stop Editing?",
                warnText = "This workout plan will be saved in draft"
            )
        }
    }
}

@Preview
@Composable
private fun MakeWorkoutPlanScreenPreview() {
    GymifyTheme {
        MakeWorkoutPlanScreen(
            onNavigateBack = { },
            onNavigateToMuscleGroupScreen = { },
            state = MakeWorkoutPlanState(
                workoutPlanName = "",
                selectedMuscleGroupName = "",
                selectedExerciseName = "",
                numberOfSets = "3",
                numberOfReps = "10",
                weight = "100",
                isPredefinedPlan = true,
            ),
            onAction = { },
        )
    }
}





