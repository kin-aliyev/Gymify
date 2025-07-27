package com.example.gymify.home.presentation.workout_complete_screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.home.presentation.components.BigActionButton
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay

@Composable
fun WorkoutCompleteScreen(
    modifier: Modifier = Modifier,
    state: WorkoutCompleteState,
    onAction: (WorkoutCompleteAction) -> Unit,
) {
    var showAnimate by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(0L)
        showAnimate = true
    }

    val confettiScale by animateFloatAsState(
        targetValue = if (showAnimate) 1f else 0.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "confetti_scale"
    )

    val confettiOffsetY by animateFloatAsState(
        targetValue = if (showAnimate) 0f else -150f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessVeryLow
        ),
        label = "confetti_offset"
    )

    val confettiAlpha by animateFloatAsState(
        targetValue = if (showAnimate) 1f else 0f,
        animationSpec = tween(durationMillis = 900),
        label = "confetti_alpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(R.drawable.icon_confetti),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .size(675.dp)
                .alpha(confettiAlpha)
                .scale(confettiScale)
                .offset(y = confettiOffsetY.dp)

        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(R.drawable.icon_trophy),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))

            Text(
                text = "Congratulations!",
                fontWeight = FontWeight.Bold,
                fontFamily = rubikFontFamily,
                fontSize = 32.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF8F9BD7),
                            Color(0XFF5F699B)
                        )
                    )
                )
            )

            Spacer(Modifier.height(24.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF8F9BD7),
                                Color(0XFF5F699B)
                            )
                        ), shape = RoundedCornerShape(18.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_clock),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(45.dp)
                    )

                    Text(
                        text = formatDuration(state.workoutTimeSeconds),
                        fontFamily = rubikFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFEBEBEB)
                    )

                }
            }
        }

        BigActionButton(
            actionTitle = "OK",
            onButtonClick = { onAction(WorkoutCompleteAction.OnDoneClick)},
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp)
                .padding(bottom = 20.dp)
        )
    }
}

private fun formatDuration(seconds: Int): String {
    val minutes = seconds / 60
    val hours = minutes / 60
    val remainingMinutes = minutes % 60

    return when {
        hours > 0 && remainingMinutes > 0 -> "${hours}hr ${remainingMinutes}min"
        hours > 0 -> "$hours hr"
        else -> "$remainingMinutes min"
    }
}

@Preview
@Composable
private fun WorkoutCompleteScreenPreview() {
    GymifyTheme {
        WorkoutCompleteScreen(
            state = WorkoutCompleteState(
                workoutTimeSeconds = 2400,
                isActiveAnimation = true
            ),
            onAction = { }
        )
    }
}