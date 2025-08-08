package com.example.gymify.settings.presentation.userpreferences.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.settings.presentation.components.ConfirmationButton
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNumericSettingTab(
    modifier: Modifier = Modifier,
    settingName: String,
    settingValueString: String,
    settingNumericValue: String,
    onChangeUserValue: (Float) -> Unit,
) {
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    var showBottomSheet by remember { mutableStateOf(false) }

    var numericValue by remember { mutableStateOf(settingNumericValue) }

    val dynamicWidth = remember(numericValue.length) {
        (110 + numericValue.length * 10).dp.coerceAtMost(200.dp)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(start = 24.dp, end = 16.dp)
    ) {
        Text(
            text = settingName,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.Medium,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF4666B1),
                        Color(0xFF617BB6)
                    )
                )
            ),
            fontSize = 20.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = settingValueString,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                color = Color(0xFFEBEBEB)
            )

            IconButton(
                onClick = { showBottomSheet = true },
                modifier = Modifier.size(44.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { showBottomSheet = false },
            containerColor = MaterialTheme.colorScheme.surface,
            dragHandle = null,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 430.dp)
                    .padding(vertical = 24.dp, horizontal = 30.dp)
            ) {
                Text(
                    text = settingName,
                    color = Color(0xFFEBEBEB),
                    fontSize = 20.sp,
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(20.dp))

                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary.copy(0.2f)
                )

                Spacer(Modifier.height(30.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(dynamicWidth)
                        .height(100.dp)
                        .border(
                            width = 4.dp,
                            color = Color(0xFF3B4051),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .drawBehind {
                            val underlineY = size.height
                            drawLine(
                                color = Color(0xFF3B4051),
                                start = Offset(x = size.width / 6, y = underlineY * 3.9f / 5),
                                end = Offset(x = size.width * 5 / 6, y = underlineY * 3.9f / 5),
                                strokeWidth = 12f
                            )
                        }

                ) {
                    BasicTextField(
                        value = numericValue,
                        onValueChange = { numericValue = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color(0xFFEBEBEB),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = rubikFontFamily,
                            textAlign = TextAlign.Center,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp)

                    )
                }

                Spacer(Modifier.height(30.dp))

                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary.copy(0.2f)
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ConfirmationButton(
                        isConfirmButton = false,
                        onClick = { showBottomSheet = false },
                        buttonText = "Cancel"
                    )

                    ConfirmationButton(
                        isConfirmButton = true,
                        onClick = {
                            if (numericValue.toInt() in 10..<1000) {
                                onChangeUserValue(numericValue.toFloat())
                                showBottomSheet = false
                            } else {
                                Toast.makeText(
                                    context,
                                    "Invalid value entered",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        buttonText = "OK"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChangeNumericSettingTabPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            ChangeNumericSettingTab(
                settingName = "Age",
                settingValueString = "28 years",
                onChangeUserValue = {},
                settingNumericValue = "200"
            )
        }
    }

}