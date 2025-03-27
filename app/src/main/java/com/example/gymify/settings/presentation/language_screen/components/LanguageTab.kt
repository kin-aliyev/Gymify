package com.example.gymify.settings.presentation.language_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

enum class LanguageTabItem(
    @DrawableRes var icon: Int,
    val languageName: String,
    val locale: String
) {
    English(R.drawable.usa_flag, languageName = "English (US)", locale = "en"),
    Russian(R.drawable.russian_flag, languageName = "Русский язык", locale = "ru"),
    Azerbaijan(R.drawable.azerbaijani_flag, languageName = "Azərbaycan dili", locale = "az")
}

@Composable
fun LanguageTab(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    languageName: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) Modifier.background(
        brush = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.primary,
                Color(0xFF617BB6)
            )
        ),
        shape = RoundedCornerShape(22.dp)
    ) else Modifier.background(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(22.dp)
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(backgroundColor)
            .clickable(onClick = onClick, role = Role.Button)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(52.dp)
            )

            Spacer(Modifier.width(20.dp))

            Text(
                text = languageName,
                fontSize = 16.sp,
                fontFamily = rubikFontFamily,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = Color(0XFFEBEBEB)
            )

        }
    }

}


@Preview
@Composable
private fun LanguageTabPreview() {
    GymifyTheme {
        LanguageTab(
            icon = R.drawable.usa_flag,
            languageName = "English (US)",
            isSelected = false,
            onClick = { }
        )
    }
}

