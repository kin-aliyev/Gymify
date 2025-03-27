@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymify.settings.presentation.app_appearance_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.core.domain.model.ThemeMode
import com.example.gymify.core.util.getLocalizedName
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ThemeSelectionBottomSheet(
    modifier: Modifier = Modifier,
    show: Boolean,
    selectedTheme: ThemeMode,
    onThemeSelected: (ThemeMode) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
//    if (show) {
    val modalBottomSheetState = rememberModalBottomSheetState()


    // Открываем BottomSheet сразу при первом рендере
    LaunchedEffect(Unit) {
        modalBottomSheetState.show()
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = null,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
//                .heightIn(min = 200.dp, max = 600.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
//                    .height(400.dp)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 28.dp)
        ) {
//                Spacer(Modifier.height(12.dp))
            Text(
                text = "Choose Theme",
                fontSize = 21.sp,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFFFFFF)
            )

            Spacer(Modifier.height(20.dp))

            HorizontalDivider(
                thickness = 1.dp, color = Color(0xFF4666B1).copy(alpha = 0.3f),
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(24.dp))

            ThemeOption(
                themeMode = ThemeMode.SYSTEM,
                isSelected = selectedTheme == ThemeMode.SYSTEM,
                onSelect = { onThemeSelected(ThemeMode.SYSTEM) },
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            Spacer(Modifier.height(12.dp))

            ThemeOption(
                themeMode = ThemeMode.LIGHT,
                isSelected = selectedTheme == ThemeMode.LIGHT,
                onSelect = { onThemeSelected(ThemeMode.LIGHT) },
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            Spacer(Modifier.height(12.dp))

            ThemeOption(
                themeMode = ThemeMode.DARK,
                isSelected = selectedTheme == ThemeMode.DARK,
                onSelect = { onThemeSelected(ThemeMode.DARK) },
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            Spacer(Modifier.height(24.dp))

            HorizontalDivider(
                thickness = 1.dp, color = Color(0xFF4666B1).copy(alpha = 0.3f),
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ChooseButton(
                    isConfirmButton = false,
                    onClick = onDismiss,
                    buttonText = "Cancel"
                )

                ChooseButton(
                    isConfirmButton = true,
                    onClick = onConfirm,
                    buttonText = "OK"
                )
            }
        }
    }
//    }
}

@Composable
fun ChooseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    isConfirmButton: Boolean,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor = if (isConfirmButton) {
        Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    Color(0XFF617BB6)
                )
            ),
            shape = RoundedCornerShape(28.dp)
        )
    } else Modifier.background(color = Color(0xFF7E97D0), shape = RoundedCornerShape(28.dp))

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(height = 55.dp, width = 150.dp)
            .then(backgroundColor)
            .clip(RoundedCornerShape(28.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = false, color = Color.Gray),
                onClick = onClick,
                role = Role.Button
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 20.sp,
            color = if (isConfirmButton) Color.White
            else MaterialTheme.colorScheme.background,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ThemeOption(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onSelect: () -> Unit,
    themeMode: ThemeMode,
) {
    val context = LocalContext.current
    val backgroundColor = if (isSelected) {
        Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    Color(0XFF617BB6)
                )
            ),
            shape = RoundedCornerShape(24.dp)
        )
    } else Modifier.background(color = Color(0XFF3B4051), shape = RoundedCornerShape(24.dp))

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth(fraction = 0.9f)
            .then(backgroundColor)
            .clickable(onClick = onSelect, role = Role.RadioButton)
            .padding(vertical = 14.dp)
    ) {
        Text(
            text = themeMode.getLocalizedName(context),
            color = Color(0XFF1F1F1F),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = rubikFontFamily
        )
    }

}

@Preview()
@Composable
private fun ThemeSelectionBottomSheetPreview() {
    GymifyTheme {
        ThemeSelectionBottomSheet(
            show = true,
            selectedTheme = ThemeMode.DARK,
            onThemeSelected = { },
            onConfirm = { },
            onDismiss = { }
        )
    }
}