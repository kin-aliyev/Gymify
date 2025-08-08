package com.example.gymify.settings.presentation.userpreferences.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.settings.presentation.components.ConfirmationButton
import com.example.gymify.settings.presentation.components.SettingsRadioButton
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ChangeOptionBottomSheetTab(
    modifier: Modifier = Modifier,
    settingName: String,
    selectedOption: T,
    options: List<T>,
    onOptionChange: (T) -> Unit,
    optionToString: (T) -> String,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    var currentOption by remember { mutableStateOf(selectedOption) }

//    Column { //TODO проверить как без column работает
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
                text = optionToString(selectedOption),
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                color = Color(0xFFEBEBEB)
            )

            IconButton(
                onClick = {
                    currentOption = selectedOption
                    showBottomSheet = true
                          },
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
                    .heightIn(max = 420.dp)
                    .padding(vertical = 24.dp, horizontal = 30.dp)
            ) {
                Text(
                    text = settingName,
                    color = Color(0xFFEBEBEB),
                    fontSize = 22.sp,
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(20.dp))

                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary.copy(0.2f)
                )

                Spacer(Modifier.height(24.dp))

                options.forEachIndexed { index, option ->
                    SettingsRadioButton(
                        text = optionToString(option),
                        isSelected = currentOption == option,
                        onSelect = { currentOption = option }
                    )

                    if (index != options.lastIndex) {
                        Spacer(Modifier.height(20.dp))
                    }
                }

                Spacer(Modifier.height(24.dp))

                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary.copy(0.2f)
                )

                Spacer(Modifier.height(20.dp))

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
                            onOptionChange(currentOption)
                            showBottomSheet = false
                        },
                        buttonText = "OK"
                    )
                }
            }
        }
    }

//    }
}

@Preview
@Composable
private fun ChangeGenderTabPreview() {
    GymifyTheme {
        ChangeOptionBottomSheetTab(
            selectedOption = UserGender.MALE,
            options = UserGender.entries,
            onOptionChange = {},
            optionToString = {"das"},
            settingName = "Gender"
        )
    }

}