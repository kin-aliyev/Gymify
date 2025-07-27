package com.example.gymify.home.presentation.muscle_group_screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.home.util.MuscleGroupIconMapper
import com.example.gymify.home.util.MuscleGroupNameMapper
import com.example.gymify.home.domain.model.MuscleGroup
import com.example.gymify.home.presentation.components.ExerciseGridItem
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuscleGroupScreen(
    modifier: Modifier = Modifier,
    onMuscleGroupClick: (MuscleGroup) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier,
        topBar = {
            BackTopBar(
                title = "Muscle Group",
                scrollBehavior = scrollBehavior,
                onBackIconClick = onNavigateBack,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = paddingValues.calculateTopPadding() + 12.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        ) {

            itemsIndexed((MuscleGroup.entries)) { index, muscleGroup ->
                val backgroundShape = index % 2 == 0
                ExerciseGridItem(
                    exerciseName = MuscleGroupNameMapper.getName(muscleGroup.nameKey),
                    iconFirst = MuscleGroupIconMapper.getIcon(muscleGroup.nameKey),
                    onClick = {
                        onMuscleGroupClick(muscleGroup)
                    },
                    backgroundShape = backgroundShape,
                )

            }
        }

    }
}

@Preview
@Composable
private fun MuscleGroupScreenPreview() {
    GymifyTheme {
        MuscleGroupScreen(
            onMuscleGroupClick = {},
            onNavigateBack = {},
        )
    }

}