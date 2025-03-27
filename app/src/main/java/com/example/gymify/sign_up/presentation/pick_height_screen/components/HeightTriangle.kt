package com.example.gymify.sign_up.presentation.pick_height_screen.components


import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.ui.theme.mainColor

@Preview
@Composable
fun HeightTriangle(
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val triangleHeightPx = 30.dp.toPx()
        val triangleBasePx = 25.dp.toPx()
        val centerYPx = 364.dp.toPx() / 2

        // Уменьшенные размеры для внутреннего треугольника
        val innerTriangleHeightPx = triangleHeightPx * 0.9f
        val innerTriangleBasePx = triangleBasePx * 0.9f

        // Смещение для выравнивания центра
        val offsetX = (triangleHeightPx - innerTriangleHeightPx) / 2
        val offsetY = (triangleBasePx - innerTriangleBasePx) / 2

        // Путь для внутреннего треугольника
        val innerTrianglePath = Path().apply {
            moveTo(innerTriangleHeightPx / 3 + offsetX + 6, centerYPx)
            lineTo(innerTriangleHeightPx + offsetX, centerYPx - innerTriangleBasePx / 2 + offsetY)
            lineTo(innerTriangleHeightPx + offsetX, centerYPx + innerTriangleBasePx / 2 - offsetY)
            close()
        }

        // Путь для внешнего треугольника
        val trianglePath = Path().apply {
            moveTo(triangleHeightPx / 3, centerYPx)
            lineTo(triangleHeightPx, centerYPx - triangleBasePx / 2)
            lineTo(triangleHeightPx, centerYPx + triangleBasePx / 2)
            close()
        }

        // Рисуем внутренний треугольник (меньше)
        drawPath(
            path = innerTrianglePath,
            color = Color(0xFF4666B1),
            style = Fill,
        )

        // Рисуем внешний треугольник (с обводкой и закругленными углами)
        drawPath(
            path = trianglePath,
            color = mainColor,
            style = Stroke(width = 4.dp.toPx(), pathEffect = PathEffect.cornerPathEffect(4.dp.toPx()))
        )
    }
}
