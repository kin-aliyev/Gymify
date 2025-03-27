package com.example.gymify.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    isAddButton: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor = if (isAddButton) {
        Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    Color(0XFF617BB6)
                )
            ),
            shape = RoundedCornerShape(28.dp)
        )
    } else Modifier.background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(28.dp))

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(height = 55.dp, width = 150.dp)
            .then(backgroundColor)
            .clip(RoundedCornerShape(28.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = false, color = Color.Gray),
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 17.sp,
            color = if (isAddButton) MaterialTheme.colorScheme.background
                else MaterialTheme.colorScheme.primary,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun AddButtonPreview() {
    GymifyTheme {
        AddButton(
            onClick = { },
            buttonText = "Add",
            isAddButton = true
        )
    }
}