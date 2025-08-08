package com.example.gymify.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.settings.presentation.language.components.AppLanguage
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun SettingTabNavigate(
    modifier: Modifier = Modifier,
    settingName: String,
    settingValue: String,
    onNavigateToSettingClick: () -> Unit,
) {
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
            modifier = Modifier.clickable(onClick = onNavigateToSettingClick, role = Role.Button)
        ) {
            Text(
                text = settingValue,
                fontFamily = rubikFontFamily,
                fontSize = 18.sp,
                color = Color(0xFFEBEBEB)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(44.dp)
            )

        }
    }
}

@Preview
@Composable
private fun SettingTabNavigatePreview() {
    GymifyTheme {
        SettingTabNavigate(
            onNavigateToSettingClick = {},
            settingName = "Language",
            settingValue = AppLanguage.ENGLISH.languageName
        )
    }
}