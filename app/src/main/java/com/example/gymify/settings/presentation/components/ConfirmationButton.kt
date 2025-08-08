package com.example.gymify.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ConfirmationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    isConfirmButton: Boolean,
) {
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
            .size(height = 55.dp, width = 140.dp)
            .then(backgroundColor)
            .clip(RoundedCornerShape(28.dp))
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 20.sp,
            color = if (isConfirmButton) Color(0xFFEBEBEB)
            else MaterialTheme.colorScheme.background,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}