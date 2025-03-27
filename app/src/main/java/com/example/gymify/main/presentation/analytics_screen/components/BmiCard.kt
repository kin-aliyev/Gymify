@file:OptIn(ExperimentalLayoutApi::class)

package com.example.gymify.main.presentation.analytics_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import java.util.Locale
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


@Composable
fun BmiCard(
    modifier: Modifier = Modifier,
    bmiValue: Float,
    sectors: List<BmiSector> = BmiSector.getBmiSectors()
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
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "BMI (kg/m2)",
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFF617BB6)
                        )
                    ),
                ),
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(14.dp))

            HorizontalDivider(thickness = 2.dp, color = Color(0XFF3B4051))

            Spacer(Modifier.height(14.dp))

            BmiChart(bmiValue = bmiValue, sectors = sectors)


        }
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BmiChart(
    modifier: Modifier = Modifier,
    bmiValue: Float,
    sectors: List<BmiSector>
) {
    val textStyle = LocalTextStyle.current.copy(
        color = Color(0xFFEBEBEB).copy(0.6f),
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        fontFamily = rubikFontFamily
    )
    val textMeasurer = rememberTextMeasurer()

    // Находим сектор, куда попадает bmiValue
    val activeSector = when {
        bmiValue < sectors.first().valueMin -> sectors.first()
        bmiValue > sectors.last().valueMax -> sectors.last()
        else -> sectors.find { bmiValue in it.valueMin..it.valueMax } ?: sectors.last()
    }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center),
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = min(size.width, size.height) / 2 * 0.8f
            val labelRadius = radius * 1.5f

            sectors.forEach {
                drawArc(
                    color = it.color,
                    startAngle = it.startAngle,
                    sweepAngle = it.sweepAngle,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(width = radius * 2, height = radius * 2),
                    style = Stroke(miter = 20f, join = StrokeJoin.Miter, width = 180f)
                )
            }


            /* The code below relates to
                drawing values of BMI */


            val boundaries = sectors.flatMap { sector ->
                listOf(
                    Pair(sector.startAngle, sector.valueMin),
                    Pair(sector.startAngle + sector.sweepAngle, sector.valueMax)
                )
            }.distinctBy { it.second }

            boundaries.forEach { (angleDeg, value) ->
                val angleRad = Math.toRadians(angleDeg.toDouble())
                // Вычисляем позицию метки по полярным координатам
                val labelPos = Offset(
                    center.x + labelRadius * cos(angleRad).toFloat(),
                    center.y + labelRadius * sin(angleRad).toFloat()
                )
                // Измеряем текст и получаем его разметку
                val textLayoutResult: TextLayoutResult = textMeasurer.measure(
                    text = value.toString(),
                    style = textStyle
                )

                val additionalXOffset = when (value) {
                    15.0f, 18.5f -> -textLayoutResult.size.width / 7f
                    30.0f, 35.0f, 40.0f -> textLayoutResult.size.width / 7f
                    else -> 0f
                }

                val additionalYOffset = when (value) {
                    15.0f -> -textLayoutResult.size.height / 2f
                    18.5f -> -textLayoutResult.size.height / 3f
                    30.0f, 35.0f -> textLayoutResult.size.height / 6f
                    40.0f -> -textLayoutResult.size.height / 2f
                    else -> 0f
                }

                // Центрируем текст относительно позиции метки
                val textPos = Offset(
                    labelPos.x - textLayoutResult.size.width / 2 + additionalXOffset,
                    labelPos.y - textLayoutResult.size.height / 2 + additionalYOffset
                )
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = textPos
                )
            }


            /* The code below relates to
                drawing triangle line on BMI */


            val clampedBmiValue = bmiValue.coerceIn(15.0f, 40.0f) // Ограничиваем значение BMI для расчета угла
            // Находим подходящий сектор для ограниченного значения BMI
            val sectorForAngle = sectors.find { clampedBmiValue in it.valueMin..it.valueMax } ?: sectors.last()

            val fraction =
                (clampedBmiValue - sectorForAngle.valueMin) / (sectorForAngle.valueMax - sectorForAngle.valueMin)
            val pointerAngle = sectorForAngle.startAngle + fraction * sectorForAngle.sweepAngle

            // Ограничиваем угол в пределах 180-360 градусов
            val constrainedPointerAngle = pointerAngle.coerceIn(180f, 360f)
            val pointerAngleRad =
                Math.toRadians(constrainedPointerAngle.toDouble()) // Переводим градусы в радианы

            val pointerLength = radius * 0.73f  // Задаём длину линии
            val triangleHeight = 60f  // расстояние от основания до острия
            val triangleBase = 40f    // ширина основания

            // Вычисляем конечную точку линии с учётом направления
            val pointerEnd = Offset(
                x = center.x + pointerLength * cos(pointerAngleRad).toFloat(),
                y = center.y + pointerLength * sin(pointerAngleRad).toFloat()
            )

            // Вычисляем точку baseCenter: от pointerEnd двигаемся назад вдоль направления указателя
            val baseCenter = Offset(
                x = pointerEnd.x - triangleHeight * cos(pointerAngleRad).toFloat(),
                y = pointerEnd.y - triangleHeight * sin(pointerAngleRad).toFloat()
            )

            // Вычисляем перпендикулярный (единичный) вектор к направлению pointerAngle: для того чтобы основание подстраивалось
            val perpX = -sin(pointerAngleRad).toFloat()
            val perpY = cos(pointerAngleRad).toFloat()

            // Вычисляем левый и правый углы основания треугольника
            val leftBase = Offset(
                x = baseCenter.x + (triangleBase / 2f) * perpX,
                y = baseCenter.y + (triangleBase / 2f) * perpY
            )
            val rightBase = Offset(
                x = baseCenter.x - (triangleBase / 2f) * perpX,
                y = baseCenter.y - (triangleBase / 2f) * perpY
            )

            // Создаем Path треугольника: начинаем с острия (pointerEnd), затем левый и правый углы основания, и замыкаем
            val trianglePath = Path().apply {
                moveTo(pointerEnd.x, pointerEnd.y)
                lineTo(leftBase.x, leftBase.y)
                lineTo(rightBase.x, rightBase.y)
                close()
            }

            // Рисуем треугольник-указатель
            drawPath(
                path = trianglePath,
                color = Color.White,
                style = Stroke(
                    width = 10.5.dp.toPx(),
                    pathEffect = PathEffect.cornerPathEffect(1.dp.toPx())
                )
            )


            /* The code below relates to
                drawing value of  current BMI
                 and his label */

            // Code for bmi current value

            val bmiText = String.format(Locale.US, "%.1f", bmiValue)

            val bmiTextStyle = textStyle.copy(
                color = activeSector.color,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            val bmiTextLayout = textMeasurer.measure(text = bmiText, style = bmiTextStyle)

            val bmiTextPos = Offset(
                x = center.x - bmiTextLayout.size.width / 2,
                y = center.y
            )

            drawText(
                textLayoutResult = bmiTextLayout,
                topLeft = bmiTextPos
            )

            // Code for category name

            val categoryTextStyle = textStyle.copy(
                color = activeSector.color,
                fontSize = 18.sp
            )

            val categoryTextLayout =
                textMeasurer.measure(text = activeSector.label, style = categoryTextStyle)

            val categoryTextPos = Offset(
                x = center.x - categoryTextLayout.size.width / 2,
                y = center.y + bmiTextLayout.size.height
            )

            drawText(
                textLayoutResult = categoryTextLayout,
                topLeft = categoryTextPos
            )
        }


        /* The code below relates to
            drawing circles of BMI
               and his label */


        FlowRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp),
            maxItemsInEachRow = 3,
            maxLines = 2,
            horizontalArrangement = Arrangement.spacedBy(36.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            sectors.forEach {
                val addedPadding = if (it == sectors.last()) {
                    Modifier.padding(start = 40.8.dp)
                } else Modifier
                CircleItem(
                    text = it.label,
                    color = it.color,
                    isActive = (it == activeSector),
                    modifier = Modifier
                        .then(addedPadding)
                )
            }
        }
    }
}

@Composable
private fun CircleItem(
    modifier: Modifier = Modifier,
    text: String = "Underweight",
    color: Color = Color(0xFF4964A1),
    isActive: Boolean = true,
) {
    val circleShape = if (isActive) {
        Modifier.background(color = color, shape = CircleShape)
    } else Modifier.border(width = 3.dp, color = color, shape = CircleShape)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .then(circleShape)
        )


        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFFFFFFFF),
            fontFamily = rubikFontFamily
        )


    }
}

@Preview
@Composable
private fun BmiCardPreview() {
    GymifyTheme {
        BmiCard(bmiValue = 23.9f)
    }
}