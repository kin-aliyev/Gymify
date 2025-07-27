package com.example.gymify.home.presentation.analytics_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.home.util.TimeConverter
import com.example.gymify.home.domain.model.TimeScale
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily


data class BarData(
    val minutes: Int,
    val label: String,
    val timestamp: Long?
)

@Composable
fun WorkoutActivityChart(
    modifier: Modifier = Modifier,
    barDataList: List<BarData>,
    isBarItemSelected: Boolean,
    selectedIndex: Int?,
    selectedDateTimestampTitle: Long?,
    selectedBarMinutes: Int?,
    selectedTimeScale: TimeScale,
    onChangeTimeScale: (TimeScale) -> Unit,
    onBarClick: (Int) -> Unit,
    totalTimeMinutes: Int? = null
) {
    val datePattern = when(selectedTimeScale) {
        TimeScale.DAY -> TimeConverter.Patterns.WEEKDAY_ANALYTICS_DATE
        TimeScale.WEEK -> TimeConverter.Patterns.FULL_DATE
        TimeScale.MONTH -> TimeConverter.Patterns.MONTH_DATE
    }

    val dateTitleString = if (selectedIndex != null && selectedTimeScale == TimeScale.MONTH) {
        "${selectedIndex + 1} Week"
    } else {
        selectedDateTimestampTitle?.let {
            TimeConverter().convertLongToDate(
                timeStamp = it,
                pattern = datePattern
            )
        }?.replaceFirstChar { it -> it.titlecase() }
    }



    val spacing = when(selectedTimeScale) {
        TimeScale.DAY -> 20.dp
        TimeScale.WEEK -> 36.dp
        TimeScale.MONTH -> 14.dp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ChartHeader(
                timeScale = selectedTimeScale,
                onChangeTimeScale = { onChangeTimeScale(it) },
                selectedDateTitle = dateTitleString
            )

            if (isBarItemSelected) {
                Spacer(Modifier.height(6.dp))

                Text(
                    text = selectedBarMinutes?.let { formatDuration(it) } ?: "",
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 32.sp,
                    color = Color(0xFFEBEBEB)
                )
            }

            if (isBarItemSelected) {
                Spacer(Modifier.height(16.dp))
            } else {
                Spacer(Modifier.height(26.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                barDataList.forEachIndexed { index, barData ->
                    ChartItem(
                        minutes = barData.minutes,
                        label = barData.label,
                        isSelected = selectedIndex == index,
                        timeScale = selectedTimeScale,
                        onClick = { onBarClick(index) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(9.dp))

            HorizontalDivider(thickness = 3.dp, color = Color(0xFF3B4051))

            Spacer(Modifier.height(9.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    fontFamily = rubikFontFamily,
                    color = Color(0xFFEBEBEB),
                )

                Text(
                    text = totalTimeMinutes?.let { formatDuration(it) } ?: "unknown",
                    fontFamily = rubikFontFamily,
                    color = Color(0xFFEBEBEB),
                )
            }
        }
    }
}

private fun formatDuration(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60

    return when {
        hours > 0 && remainingMinutes > 0 -> "${hours}h ${remainingMinutes}m"
        hours > 0 -> "$hours h"
        else -> "$remainingMinutes m"
    }
}

@Preview
@Composable
private fun WorkoutActivityChartPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            WorkoutActivityChart(
                selectedTimeScale = TimeScale.DAY,
                selectedBarMinutes = 130,
                isBarItemSelected = true,
                onChangeTimeScale = {},
                selectedDateTimestampTitle = null,
                barDataList = listOf(
                    BarData(120, "Mon", null),
                    BarData( 30, "Tue", null),
                    BarData( 45, "Wed", null),
                    BarData( 60, "Thu", null),
                    BarData( 80, "Fri", null),
                    BarData( 100, "Sat", null),
                    BarData( 120, "Sun", null),
                ),
                selectedIndex = 0,
                onBarClick = {},
                totalTimeMinutes = 800
            )
        }
    }

}

@Composable
private fun ChartItem(
    minutes: Int,
    label: String,
    isSelected: Boolean,
    timeScale: TimeScale,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val maxBarHeight = 110.dp
    val barHeightRatio = when (timeScale) {
        TimeScale.DAY -> (minutes / 120f).coerceIn(0f, 1f)
        TimeScale.WEEK -> (minutes / 360f).coerceIn(0f, 1f)
        TimeScale.MONTH -> (minutes / 1440f).coerceIn(0f, 1f)
    }
    val barHeight = maxBarHeight * barHeightRatio

    val modifierBackground = if (isSelected) Modifier.background(
        brush = Brush.linearGradient(
            colors = listOf(Color(0xFF4666B1), Color(0xFF617BB6))
        ), shape = RoundedCornerShape(8.dp)
    ) else Modifier.background(color = Color(0xFF3B4051), shape = RoundedCornerShape(8.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(maxBarHeight) // фиксированная высота
                .fillMaxWidth()
                .clickable(onClick = onClick, role = Role.Checkbox),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .height(barHeight)
                    .fillMaxWidth()
                    .then(modifierBackground)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = label,
            fontFamily = rubikFontFamily,
            fontSize = 15.sp,
            color = Color(0xFFEBEBEB)
        )
    }
}

//@Preview
//@Composable
//private fun WorkoutActivityChartItemPreview() {
//    GymifyTheme {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            WorkoutActivityChartItem(
//                hours = 2f,
//                label = "Mon",
//                isSelected = true,
//                timeScale = TimeScale.DAY,
//                onClick = {}
//            )
//        }
//    }
//
//}