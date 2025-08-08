package com.example.gymify.home.presentation.workout_plan_detail_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.gymify.R
import com.example.gymify.home.presentation.components.CustomAlertDialog
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun WorkoutPlanBanner(
    modifier: Modifier = Modifier,
    imagePath: String? = null,
    @DrawableRes workoutIconId: Int? = null,
    onNavigateBackClick: () -> Unit,
    onDeleteWorkoutClick: () -> Unit,
    onEditWorkoutClick: () -> Unit,
    isWorkoutInDatabase: Boolean,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        when {
            imagePath != null -> {
                AsyncImage(
                    model = imagePath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            workoutIconId != null -> {
                Image(
                    painter = painterResource(workoutIconId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 36.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(42.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
                    .clickable(onClick = onNavigateBackClick, role = Role.Button)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.surface)
                        .clickable(onClick = { showEditDialog = true }, role = Role.Button)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }


                if (isWorkoutInDatabase) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colorScheme.surface)
                            .clickable(onClick = { showDeleteDialog = true }, role = Role.Button)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
        if (showDeleteDialog) {
            CustomAlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                onConfirmation = {
                    showDeleteDialog = false
                    onDeleteWorkoutClick()
                },
                title = "Delete this workout?",
                warnText = "This action cannot be undone"
            )

        }
        if (showEditDialog) {
            CustomAlertDialog(
                onDismissRequest = { showEditDialog = false },
                onConfirmation = {
                    showEditDialog = false
                    onEditWorkoutClick()
                },
                title = "Edit this workout?",
                warnText = "Your workout plan will be saved"
            )

        }

    }
}

@Preview
@Composable
private fun WorkoutPlanBannerPreview() {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            WorkoutPlanBanner(
                workoutIconId = R.drawable.flag_russia,
                onNavigateBackClick = { },
                onEditWorkoutClick = { },
                onDeleteWorkoutClick = { },
                isWorkoutInDatabase = true,

            )
        }
    }
}