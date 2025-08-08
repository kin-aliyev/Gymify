package com.example.gymify.signup.presentation.choose_level_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily


@Composable
fun LevelButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onLevelClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    Color(0xFF617BB6)
                )
            ),
            shape = RoundedCornerShape(24.dp)
        )
    } else Modifier.background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(24.dp))

    Button(
        onClick = onLevelClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .then(backgroundColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 11.dp)
        )
    }
}


@Preview
@Composable
private fun LevelButtonPreview() {
    GymifyTheme {
        LevelButton(
            text = "Begginer",
            isSelected = true,
            onLevelClick = { }
        )
    }
}