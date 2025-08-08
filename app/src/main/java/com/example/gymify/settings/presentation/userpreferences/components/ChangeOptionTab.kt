package com.example.gymify.settings.presentation.userpreferences.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.util.getLocalizedName
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun <T> ChangeOptionTab(
    modifier: Modifier = Modifier,
    settingName: String,
    settingValue: String,
    options: List<T>,
    onOptionClick: (T) -> Unit,
    optionToString: (T) -> String,
) {
    var expanded by remember { mutableStateOf(false) }

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
            fontSize = 19.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = settingValue,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                color = Color(0xFFEBEBEB)
            )

            Column {
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(0.dp),
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.background.copy(0.6f))
                ) {
                    options.forEachIndexed { index, option ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onOptionClick(option)
                                    expanded = false
                                }
                                .padding(vertical = 12.dp, horizontal = 26.dp), // отступы по вкусу
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = optionToString(option),
                                fontFamily = rubikFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )
                        }

                        if (index != options.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.background.copy(0.5f))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChangeOptionTabPreview() {
    GymifyTheme {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            ChangeOptionTab(
                settingName = "Weight Unit",
                settingValue = "kg",
                options = UserWeightUnit.entries,
                onOptionClick = { },
                optionToString = { it.getLocalizedName(context = context)}
            )
        }
    }
}