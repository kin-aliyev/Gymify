package com.example.gymify.main.presentation.make_workoutplan_screen.components

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
import androidx.compose.foundation.layout.wrapContentSize
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
    hasUnderline: Boolean = false,
    isChooseNum: Boolean = false,
    onChooseNumClick: () -> Unit = {},
) {
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
                .height(70.dp)
                .padding(horizontal = 26.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 20.sp,
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
                modifier = Modifier
                    .clickable { onChooseNumClick() }
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF3B4051)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .sizeIn(
                        minWidth = if (isChooseNum) 30.dp else 130.dp,
                        minHeight = 22.dp
                    )
                    .then(
                        if (hasUnderline) {
                            Modifier.drawBehind {
                                val underlineY =
                                    size.height  // Высота Box, чтобы нарисовать линию внизу
                                drawLine(
                                    color = Color(0xFF3B4051),
                                    start = Offset(0f, underlineY),
                                    end = Offset(size.width, underlineY),
                                    strokeWidth = 3f
                                )
                            }
                        } else Modifier
                    )
                    .wrapContentSize()
            ) {
                if (exerciseValue.isNotEmpty()) {
                    Text(
                        text = exerciseValue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                    )
                }
            }
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
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 12.dp)
                .padding(start = 26.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 20.sp,
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = muscleGroupName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )

                Spacer(Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(44.dp)
                        .clickable { onMuscleGroupClick() }
                )

            }

        }
    }
}

@Composable
fun OptionTabSets(
    modifier: Modifier = Modifier,
    optionName: String,
    numOfSets: String,
    onNumOfSetsChanged: (String) -> Unit,
) {
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
                .height(70.dp)
                .padding(horizontal = 26.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 20.sp,
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
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .sizeIn(
                        minWidth = 30.dp,
                        minHeight = 24.dp
                    ) // Увеличил размеры, чтобы текст не прижимался
                    .drawBehind {
                        val underlineY = size.height
                        drawLine(
                            color = Color(0xFF3B4051),
                            start = Offset(x = 0f, y = underlineY),
                            end = Offset(x = size.width, y = underlineY),
                            strokeWidth = 3f
                        )
                    }
            ) {
                BasicTextField(
                    value = numOfSets,
                    onValueChange = { onNumOfSetsChanged(it) },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center // Выравнивание текста по центру
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .matchParentSize()
                        .padding(top = 3.dp)
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
                .height(70.dp)
                .padding(horizontal = 26.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = optionName,
                fontFamily = rubikFontFamily,
                fontSize = 20.sp,
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
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .size(width = 61.dp, height = 24.dp) // Увеличил размеры, чтобы текст не прижимался
            ) {
                Row {
                    BasicTextField(
                        value = weight,
                        onValueChange = { onWeightChange(it) },
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center // Выравнивание текста по центру
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .size(width = 38.dp, height = 24.dp)
                            .padding(top = 3.dp)
                            .drawBehind {
                                val underlineY = size.height
                                drawLine(
                                    color = Color(0xFF3B4051),
                                    start = Offset(x = 0f, y = underlineY),
                                    end = Offset(x = size.width, y = underlineY),
                                    strokeWidth = 3f
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
                numOfSets = "12",
                onNumOfSetsChanged = { }
            )
            OptionTabSets(
                optionName = "The number of reps",
                numOfSets = "4",
                onNumOfSetsChanged = { }
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