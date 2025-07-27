package com.example.gymify.home.presentation.workout_session_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun CustomTimer(
    modifier: Modifier = Modifier,
    timeElapsedSeconds: Int
) {
    Text(
        text = formatElapsedTime(timeElapsedSeconds),
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = Color(0xFFEBEBEB),
    )
}

fun formatElapsedTime(seconds: Int): String {
    val hrs = seconds / 3600
    val mins = (seconds % 3600) / 60
    val secs = seconds % 60
    return "%02d : %02d : %02d".format(hrs, mins, secs)
}

@Preview
@Composable
private fun TimerPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CustomTimer(
                timeElapsedSeconds = 14300
            )
        }
    }
}