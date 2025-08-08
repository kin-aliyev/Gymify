package com.example.gymify.signup.presentation.pick_weight_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.ui.theme.mainColor

@Preview
@Preview
@Composable
fun WeightTriangle(
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val triangleHeightPx = 20.dp.toPx()
        val triangleBasePx = 23.dp.toPx()
        val centerXPx = size.width / 2

        // Уменьшенные размеры для внутреннего треугольника
        val innerTriangleHeightPx = triangleHeightPx * 0.9f
        val innerTriangleBasePx = triangleBasePx * 0.9f

        // Смещение для выравнивания центра
        val offsetX = (triangleBasePx - innerTriangleBasePx) / 2
        val offsetY = (triangleHeightPx - innerTriangleHeightPx) / 2

        // Путь для внутреннего треугольника
        val innerTrianglePath = Path().apply {
            moveTo(centerXPx, 0f + offsetY) // Верхняя вершина
            lineTo(centerXPx - innerTriangleBasePx / 2 + offsetX, innerTriangleHeightPx + offsetY) // Левое основание
            lineTo(centerXPx + innerTriangleBasePx / 2 - offsetX, innerTriangleHeightPx + offsetY) // Правое основание
            close()
        }

        // Путь для внешнего треугольника
        val trianglePath = Path().apply {
            moveTo(centerXPx, 0f) // Верхняя вершина
            lineTo(centerXPx - triangleBasePx / 2, triangleHeightPx) // Левое основание
            lineTo(centerXPx + triangleBasePx / 2, triangleHeightPx) // Правое основание
            close()
        }

        // Рисуем внутренний треугольник (меньше)
        drawPath(
            path = innerTrianglePath,
            color = mainColor,
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

