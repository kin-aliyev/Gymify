package com.example.gymify.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun CustomAlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String? = null,
    warnText: String? = null
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Color(0xFFEBEBEB),
                    modifier = Modifier
                        .size(44.dp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = title ?: "Delete this exercise?",
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFEBEBEB),
                    fontFamily = rubikFontFamily
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = warnText ?: "You will not be able recover it",
                    fontWeight = FontWeight.Light,
                    color = Color(0xFFEBEBEB).copy(0.7f),
                    fontFamily = rubikFontFamily,
                    fontSize = 13.sp
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(8.dp))
                            .height(42.dp)
                            .padding(horizontal = 4.dp),
                    ) {
                        Text(
                            text = "Dismiss",
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFEBEBEB),
                            fontSize = 13.sp
                        )
                    }

                    Spacer(Modifier.width(24.dp))

                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp))
                            .height(42.dp)
                            .padding(horizontal = 4.dp)
                    ) {
                        Text(
                            text = "Confirm",
                            fontFamily = rubikFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 13.sp
                        )
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun AlertDialogPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CustomAlertDialog(
                onDismissRequest = { },
                onConfirmation = { }
            )
        }
    }
}