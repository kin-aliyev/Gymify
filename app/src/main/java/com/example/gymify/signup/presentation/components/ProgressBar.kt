package com.example.gymify.signup.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    progress: Float = 0.25f
) {

    Canvas(
        modifier = modifier
            .fillMaxWidth(0.6f)
            .height(4.dp)
    ) {
        drawRoundRect(
            color = backgroundColor,
            size = Size(size.width, size.height),
            cornerRadius = CornerRadius(50f, 50f)
        )

        drawRoundRect(
            color = progressColor,
            size = Size(size.width * progress, size.height),
            cornerRadius = CornerRadius(50f, 50f)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProgressBarPreview() {
    GymifyTheme {
        ProgressBar()
    }
}