package com.example.gymify.home.presentation.make_workoutplan_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun OptionTabName(
    modifier: Modifier = Modifier,
    optionName: String,
    exerciseValue: String,
    hasUnderline: Boolean = false
) {
    val dynamicWidth = when (exerciseValue.length) {
        in 0..5 -> 60.dp
        in 6..8 -> 80.dp
        in 9..11 -> 100.dp
        in 12..14 -> 120.dp
        in 15..17 -> 140.dp
        in 18..20 -> 160.dp
        else -> 170.dp
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFF617BB6)
                        )
                    )
                )
            )

//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
////                    .border(
////                        width = 1.dp,
////                        shape = RoundedCornerShape(10.dp),
////                        color = Color(0xFF3B4051)
////                    )
//                    .padding(horizontal = 12.dp, vertical = 6.dp)
//                    .width(dynamicWidth)
//                    .height(22.dp)
//                    .drawBehind {
//                        if (hasUnderline) {
//                            val underlineY =
//                                size.height  // Высота Box, чтобы нарисовать линию внизу
//                            drawLine(
//                                color = Color(0xFF3B4051),
//                                start = Offset(0f, underlineY + 2f),
//                                end = Offset(size.width, underlineY + 2f),
//                                strokeWidth = 2f
//                            )
//                        }
//
//                    }
//            )
//            {
                if (exerciseValue.isNotEmpty()) {
                    Text(
                        text = exerciseValue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0XFFEBEBEB),
                        fontFamily = rubikFontFamily,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                } else {
                    Text(
                        text = "Choose Muscle Group",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0XFFEBEBEB).copy(alpha = 0.45f),
                        fontFamily = rubikFontFamily,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                }
//            }
        }
    }
}

@Composable
fun OptionTabChoose(
    modifier: Modifier = Modifier,
    optionName: String,
    muscleGroupName: String,
    onMuscleGroupClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onMuscleGroupClick, role = Role.Button)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(vertical = 12.dp)
                .padding(start = 26.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFF617BB6)
                        )
                    )
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 2.dp)
            ) {
                if (muscleGroupName.isNotEmpty()) {
                    Text(
                        text = muscleGroupName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0XFFEBEBEB),
                        fontFamily = rubikFontFamily
                    )
                } else {
                    Text(
                        text = "Select Group",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFEBEBEB).copy(0.8f),
                        fontFamily = rubikFontFamily
                    )
                }

                Spacer(Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(44.dp)

                )

            }

        }
    }
}

@Composable
fun OptionTabSets(
    modifier: Modifier = Modifier,
    optionName: String,
    inputValue: String,
    onInputValueChanged: (String) -> Unit,
    isForPlanName: Boolean = false,
    isReadOnly: Boolean = false,
) {
    // Динамическая ширина на основе длины текста
    val dynamicWidth = if (isForPlanName) {
        // Для названия плана - больше места
        when (inputValue.length) {
            in 0..17 -> 150.dp
            in 18..20 -> 160.dp
            else -> 185.dp
        }
    } else {
        // Для чисел (sets/reps) - компактно
        when (inputValue.length) {
            0, 1 -> 30.dp
            2 -> 40.dp
            3 -> 50.dp
            4 -> 55.dp
            else -> 60.dp
        }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFF617BB6)
                        )
                    )
                )
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = if (isForPlanName) 1.dp else 2.dp,
                        color = Color(0xFF3B4051),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 9.dp, vertical = 6.dp)
                    .width(dynamicWidth)
                    .height(24.dp)
                    .drawBehind {
                        val underlineY = size.height
                        drawLine(
                            color = Color(0xFF3B4051),
                            start = Offset(x = 0f, y = underlineY + 2.5f),
                            end = Offset(x = size.width, y = underlineY + 2.5f),
                            strokeWidth = if (isForPlanName) 1f else 2f
                        )
                    }
            ) {
                BasicTextField(
                    value = inputValue,
                    onValueChange = { onInputValueChanged(it) },
                    textStyle = TextStyle(
                        color = if (isReadOnly) Color(0xFFEBEBEB).copy(alpha = 0.9f) else Color(0xFFEBEBEB),
                        fontSize = if (isForPlanName) 16.sp else 17.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = rubikFontFamily
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    readOnly = isReadOnly,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun OptionTabWeight(
    modifier: Modifier = Modifier,
    optionName: String,
    weight: String,
    onWeightChange: (String) -> Unit,
    userWeightUnit: UserWeightUnit,
) {
    // Динамическая ширина на основе длины текста
    val dynamicWidth = when (weight.length) {
        0, 1 -> 50.dp
        2 -> 55.dp
        3 -> 60.dp
        4 -> 70.dp
        5 -> 80.dp
        else -> 90.dp
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFF617BB6)
                        )
                    )
                )
            )

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color(0xFF3B4051),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 9.dp, vertical = 6.dp)
                    .width(dynamicWidth)
                    .height(24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = weight,
                        onValueChange = { onWeightChange(it) },
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .drawBehind {
                                val underlineY = size.height
                                drawLine(
                                    color = Color(0xFF3B4051),
                                    start = Offset(x = 0f, y = underlineY + 2.5f),
                                    end = Offset(x = size.width, y = underlineY + 2.5f),
                                    strokeWidth = 2f
                                )
                            }
                    )

                    Spacer(Modifier.width(2.dp))

                    Text(
                        text = if (userWeightUnit.unitId == 0) stringResource(R.string.unit_lbs)
                        else stringResource(R.string.unit_kg),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun YourWorkoutPlansScreenPreview() {
    GymifyTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            OptionTabName(optionName = "Name", exerciseValue = "Standing Calf Raise")
            OptionTabChoose(
                optionName = "Muscle Group",
                muscleGroupName = "Calves",
                onMuscleGroupClick = { })
            OptionTabSets(
                optionName = "The number of sets",
                inputValue = "12",
                onInputValueChanged = { }
            )
            OptionTabSets(
                optionName = "The number of reps",
                inputValue = "4",
                onInputValueChanged = { }
            )
            OptionTabWeight(
                optionName = "The working weight",
                userWeightUnit = UserWeightUnit.LBS,
                weight = "1000",
                onWeightChange = { }
            )
        }
    }
}