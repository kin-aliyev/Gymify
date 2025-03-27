package com.example.gymify.sign_up.presentation.pick_height_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun HeightMeasureRuler(
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
            .height(364.dp)
            .width(108.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .padding(vertical = 10.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val centerY = size.height / 2
            val lineSpacing = size.height / 4
            val smallLineSpacing = size.height / 20

            val smallLineStartX = size.width / 2.75f
            val smallLineEndX = size.width * 1.75f / 2.75f

            val mediumLineStartX = size.width / 4
            val mediumLineEndX = size.width * 3 / 4

            // Функция рисования маленьких линий
            fun drawSmallLines(centerY: Float, count: Int, offset: Float, isPositive: Boolean) {
                for (i in 1..count) {
                    val yOffset = if (isPositive) centerY + i * smallLineSpacing
                    else centerY - i * smallLineSpacing
                    drawLine(
                        color = smallLineColor,
                        start = Offset(x = smallLineStartX, y = yOffset),
                        end = Offset(x = smallLineEndX, y = yOffset),
                        strokeWidth = smallLineThickness,
                        cap = StrokeCap.Round
                    )
                }
            }

            // Рисуем маленькие линии
            drawSmallLines(centerY, 9, offset = smallLineSpacing, isPositive = false)
            drawSmallLines(centerY, 9, offset = smallLineSpacing, isPositive = true)

            // Рисуем средние линии
            drawLine(
                color = mediumLineColor,
                start = Offset(x = mediumLineStartX, y = centerY - lineSpacing),
                end = Offset(x = mediumLineEndX, y = centerY - lineSpacing),
                strokeWidth = mediumLineThickness,
                cap = StrokeCap.Round
            )
            drawLine(
                color = mediumLineColor,
                start = Offset(x = mediumLineStartX, y = centerY + lineSpacing),
                end = Offset(x = mediumLineEndX, y = centerY + lineSpacing),
                strokeWidth = mediumLineThickness,
                cap = StrokeCap.Round
            )

            // Рисуем основную большую линию
            drawLine(
                color = mainLineColor,
                start = Offset(x = size.width / 6, y = centerY),
                end = Offset(x = size.width * 5 / 6, y = centerY),
                strokeWidth = mainLineThickness,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeasureRulerPreview() {
    GymifyTheme {
        HeightMeasureRuler()
    }
    
}