package com.example.gymify.settings.presentation.helpandsupport.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun HelpAndSupportInfoTab(
    modifier: Modifier = Modifier,
    tabValue: String,
    @DrawableRes tabIcon: Int,
    onTabClick: () -> Unit = {},
    isForBank: Boolean = false,
    isForSocialMedia: Boolean = false,
) {
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("Copied Text", tabValue)

    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = if (isForBank) Color(0xFFEBEBEB) else MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(14.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isForBank) {
                Image(
                    painter = painterResource(tabIcon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            } else {
                Icon(
                    painter = painterResource(tabIcon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
            }


            Text(
                text = tabValue,
                fontFamily = rubikFontFamily,
                fontWeight = if (isForBank) FontWeight.Normal else FontWeight.Medium,
                fontSize = if (isForBank) 16.sp else 18.sp
            )

            if (isForBank || isForSocialMedia) {
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = if (isForBank) painterResource(R.drawable.icon_copy)
                        else painterResource(R.drawable.icon_round_open_in_new),
                    contentDescription = null,
                    tint = if (isForBank)Color(0xFFEBEBEB).copy(0.8f)
                        else MaterialTheme.colorScheme.primary.copy(0.8f),
                    modifier = Modifier
                        .size(if (isForBank) 24.dp else 28.dp)
                        .clickable(
                            onClick = {
                                if (isForBank) {
                                    clipboardManager.setPrimaryClip(clipData)
                                    Toast.makeText(
                                        context, "Card number copied",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else onTabClick()
                            },
                            role = Role.Button
                        )
                )
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun HelpAndSupportInfoTabPreview() {
    GymifyTheme {
        HelpAndSupportInfoTab(
            tabValue = "Azerbaijan, Baku",
            tabIcon = R.drawable.icon_rounded_globe_location,
            isForBank = true,
            onTabClick = {}
        )
    }
}