package com.example.gymify.signup.presentation.pick_weight_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.ui.theme.GymifyTheme


@Composable
fun WeightMeasureRuler(
    modifier: Modifier = Modifier,
    smallLineColor: Color = Color.White,
    mediumLineColor: Color = Color.White,
    mainLineColor: Color = Color(0xFF4666B1),
    smallLineThickness: Float = 5f,
    mediumLineThickness: Float = 7f,
    mainLineThickness: Float = 8f
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .padding(horizontal = 10.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val centerX = size.width / 2
            val mediumLineSpacing = size.width / 4
            val smallLineSpacing = size.width / 20
            
            val smallLineStartY = size.height / 2.75f
            val smallLineEndY = size.height * 1.75f / 2.75f
            
            val mediumLineStartY = size.height / 4
            val mediumLineEndY = size.height * 3 / 4

            fun drawSmallLines(centerX: Float, count: Int, isPositive: Boolean) {
                for (i in 1..count) {
                    val xOffset = if (isPositive) centerX + i * smallLineSpacing
                        else centerX - i * smallLineSpacing
                    drawLine(
                        color = smallLineColor,
                        start = Offset(x = xOffset, y = smallLineStartY),
                        end = Offset(x = xOffset, y = smallLineEndY),
                        strokeWidth = smallLineThickness,
                        cap = StrokeCap.Round
                    )
                }
            }

            // Drawing small lines
            drawSmallLines(centerX, 9, isPositive = false)
            drawSmallLines(centerX, 9, isPositive = true)

            // Drawing medium lines
            drawLine(
                color = mediumLineColor,
                start = Offset(x = centerX - mediumLineSpacing, y = mediumLineStartY),
                end = Offset(x = centerX - mediumLineSpacing, y = mediumLineEndY),
                strokeWidth = mediumLineThickness,
                cap = StrokeCap.Round
            )
            drawLine(
                color = mediumLineColor,
                start = Offset(x = centerX + mediumLineSpacing, y = mediumLineStartY),
                end = Offset(x = centerX + mediumLineSpacing, y = mediumLineEndY),
                strokeWidth = mediumLineThickness,
                cap = StrokeCap.Round
            )

            // Drawing main line
            drawLine(
                color = mainLineColor,
                start = Offset(x = centerX, y = size.height / 6),
                end = Offset(x = centerX, y = size.height * 5 / 6),
                strokeWidth = mainLineThickness
            )

        }
    }
}

@Preview
@Composable
private fun WeightMeasureRulerPreview() {
    GymifyTheme {
        WeightMeasureRuler()
    }
}