package com.example.gymify.home.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.gymify.R
import com.example.gymify.home.util.TimeConverter
import com.example.gymify.home.util.WorkoutPlanIconMapper
import com.example.gymify.home.domain.model.WorkoutPlan
import com.example.gymify.home.util.WorkoutPlanNameMapper
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun YourWorkoutPlans(
    modifier: Modifier = Modifier,
    onAddNewWorkoutClick: () -> Unit,
    userWorkoutPlans: List<WorkoutPlan>,
    onNavigateToAllUserWorkoutPlans: () -> Unit,
    onWorkoutPlanClick: (WorkoutPlan) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .padding(bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.your_workout_plans),
                    style = TextStyle(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                Color(0xFF617BB6)
                            )
                        ),
                    ),
                    fontFamily = rubikFontFamily,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium
                )

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clickable(onClick = onNavigateToAllUserWorkoutPlans, role = Role.Button)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF4666B1),
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 8.dp)

                    )
                }

            }

            Spacer(Modifier.height(8.dp))

            if (userWorkoutPlans.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .border(
                            color = Color(0x80EBEBEB),
                            width = 3.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable(onClick = onAddNewWorkoutClick),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            tint = Color(0x80EBEBEB),
                            modifier = Modifier
                                .size(100.dp)
                                .alpha(0.8f)
                        )

                        Text(
                            text = "Create your own workout",
                            color = Color(0x80EBEBEB),
                            modifier = Modifier.offset(y = (-8).dp),
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                val recentWorkoutPlans = userWorkoutPlans
                    .sortedByDescending { it.lastUsedDate }
                    .take(2)

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    recentWorkoutPlans.forEach { workoutPlan ->
                        val formattedDate = workoutPlan.lastUsedDate?.let {
                            TimeConverter().convertLongToDate(
                                timeStamp = it,
                                pattern = TimeConverter.Patterns.SHORT_DATE_TIME
                            )
                        }?.replaceFirstChar { it -> it.titlecase() } ?: "unknown date"

                        val name = workoutPlan.workoutPlanName
                        val nameId = workoutPlan.workoutPlanNameId

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .clickable(
                                    onClick = { onWorkoutPlanClick(workoutPlan) },
                                    role = Role.Button
                                )
                        ) {
                            if (workoutPlan.iconUri != null) {
                                AsyncImage(
                                    model = workoutPlan.iconUri,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else if (workoutPlan.iconId != null) {
                                Image(
                                    painter = painterResource(
                                        WorkoutPlanIconMapper.getName(
                                            workoutPlan.iconId
                                        )
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Image(
                                    painter = painterResource(R.drawable.icon_banner_workout_plan),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(start = 16.dp, bottom = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(-2.dp)
                            ) {
                                Text(
                                    text = name ?: nameId?.let {
                                        stringResource(
                                            WorkoutPlanNameMapper.getName(
                                                it
                                            )
                                        )
                                    } ?: "Unnamed Workout Plan",
                                    fontSize = 20.sp,
                                    fontFamily = rubikFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFFEBEBEB),
                                    modifier = Modifier
                                )

                                Text(
                                    text = stringResource(R.string.last_used_date, formattedDate),
                                    fontSize = 12.sp,
                                    fontFamily = rubikFontFamily,
                                    fontWeight = FontWeight.Light,
                                    color = Color(0xFFEBEBEB),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun YourWorkoutPlansPreview() {
    GymifyTheme {
        YourWorkoutPlans(
            onAddNewWorkoutClick = { },
            onNavigateToAllUserWorkoutPlans = { },
            onWorkoutPlanClick = { },
            userWorkoutPlans = listOf<WorkoutPlan>(
                WorkoutPlan(

                ),
                WorkoutPlan(

                ),

                )
        )
    }
}

