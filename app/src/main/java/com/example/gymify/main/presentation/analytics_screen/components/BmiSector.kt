package com.example.gymify.main.presentation.analytics_screen.components

import androidx.compose.ui.graphics.Color

data class BmiSector(
    val color: Color,
    val valueMin: Float,
    val valueMax: Float,
    val startAngle: Float,
    val sweepAngle: Float,
    val label: String,
) {
    companion object {
        fun getBmiSectors(): List<BmiSector> = listOf(
            BmiSector(
                color = Color(0xFF4964A1),
                valueMin = 15.0f,
                valueMax = 18.5f,
                startAngle = 180f,
                sweepAngle = 18f,
                label = "Underweight"
            ),
            BmiSector(
                color = Color(0xFF449360),
                valueMin = 18.5f,
                valueMax = 25.0f,
                startAngle = 201f,
                sweepAngle = 48f,
                label = "Normal"
            ),
            BmiSector(
                color = Color(0xFFDCC26A),
                valueMin = 25.0f,
                valueMax = 30.0f,
                startAngle = 252f,
                sweepAngle = 36f,
                label = "Overweight"
            ),
            BmiSector(
                color = Color(0xFFD4884A),
                valueMin = 30.0f,
                valueMax = 35.0f,
                startAngle = 291f,
                sweepAngle = 31f,
                label = "Obese"
            ),
            BmiSector(
                color = Color(0xFFC44B45),
                valueMin = 35.0f,
                valueMax = 40.0f,
                startAngle = 325f,
                sweepAngle = 35f,
                label = "Morbidly Obese"
            )
        )
    }
}
