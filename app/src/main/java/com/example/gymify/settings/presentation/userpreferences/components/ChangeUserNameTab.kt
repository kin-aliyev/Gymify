package com.example.gymify.settings.presentation.userpreferences.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Clear
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ChangeUserNameTab(
    modifier: Modifier = Modifier,
    userName: String,
    onUserNameChanged: (String) -> Unit,
) {
    var currentText by remember(userName) { mutableStateOf(userName) }
    var isEditing by remember { mutableStateOf(false) }

    val dynamicWidth = remember(currentText.length) {
        (50 + currentText.length * 7).dp.coerceAtMost(180.dp)
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
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Name",
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
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color(0xFF3B4051),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(dynamicWidth)

            ) {
                BasicTextField(
                    value = currentText,
                    onValueChange = { currentText = it },
                    enabled = isEditing,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = if (isEditing) Color(0xFFEBEBEB).copy(0.8f) else Color(0xFFEBEBEB),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = rubikFontFamily,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            Spacer(Modifier.width(6.dp))

            if (isEditing) {
                IconButton(
                    onClick = {
                        onUserNameChanged(currentText)
                        isEditing = false
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(Modifier.width(5.dp))

                IconButton(
                    onClick = {
                        currentText = userName
                        isEditing = false
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = null,
                        tint = Color(0xFFC44B45).copy(0.9f),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            } else {
                Spacer(Modifier.width(5.dp))

                IconButton(
                    onClick = { isEditing = true },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary.copy(0.9f),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChangeUserNameTabPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            ChangeUserNameTab(
                userName = "Gym Bro #1",
                onUserNameChanged = {}
            )
        }
    }
}

