package com.example.gymify.analytics.presentation.analytics_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.home.domain.model.TimeScale
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ChartHeader(
    modifier: Modifier = Modifier,
    selectedDateTitle: String? = null,
    timeScale: TimeScale,
    onChangeTimeScale: (TimeScale) -> Unit,
) {

    val headerText = selectedDateTitle ?: when (timeScale) {
        TimeScale.DAY -> "This Week's Progress"
        TimeScale.WEEK -> "This Month's Progress"
        TimeScale.MONTH -> "This Year's Progress"
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = headerText,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = if (selectedDateTitle != null) 20.sp else 18.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF4666B1), Color(0xFF617BB6))
                ),
            )
        )

        TimeScaleMenu(
            onTimeScaleMenuClick = { onChangeTimeScale(it) }
        )

    }
}

@Composable
private fun TimeScaleMenu(
    modifier: Modifier = Modifier,
    onTimeScaleMenuClick: (TimeScale) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var timeScaleText by remember { mutableStateOf("Days") }

    Box(
        modifier = modifier
            .border(
                width = 3.dp, color = Color(0xFF3B4051),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = { expanded = true }, role = Role.Button)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(4.dp)
                .padding(horizontal = 6.dp)

        ) {
            Text(
                text = timeScaleText,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF4666B1), Color(0xFF617BB6))
                    ),
                )
            )

            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surface,
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.background.copy(1f)),
            shape = RoundedCornerShape(0.dp),
        ) {
            // Days
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 6.dp, bottom = 9.dp)
                    .clickable {
                        timeScaleText = "Days"
                        onTimeScaleMenuClick(TimeScale.DAY)
                        expanded = false
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Days",
                    fontFamily = rubikFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF3B4051))

            // Weeks
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 11.dp)
                    .clickable {
                        timeScaleText = "Weeks"
                        onTimeScaleMenuClick(TimeScale.WEEK)
                        expanded = false
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Weeks",
                    fontFamily = rubikFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF3B4051))

            // Months
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 9.dp, bottom = 6.dp)
                    .clickable {
                        timeScaleText = "Months"
                        onTimeScaleMenuClick(TimeScale.MONTH)
                        expanded = false
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Months",
                    fontFamily = rubikFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview
@Composable
private fun ChartHeaderPreview() {
    GymifyTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ChartHeader(
                timeScale = TimeScale.DAY,
                onChangeTimeScale = {},
                selectedDateTitle = "27 July"
            )
        }
    }
}
