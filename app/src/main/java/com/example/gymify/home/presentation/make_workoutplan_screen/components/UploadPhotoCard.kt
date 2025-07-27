package com.example.gymify.home.presentation.make_workoutplan_screen.components

import android.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun UploadPhotoCard(
    modifier: Modifier = Modifier,
    imagePath: String? = null,
    onUploadImageClick: () -> Unit,
    @DrawableRes planIconId: Int? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .border(
                color = Color(0x80EBEBEB),
                width = 2.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onUploadImageClick, role = Role.Button)
    ) {
        when {
            imagePath != null -> {
                AsyncImage(
                    model = imagePath,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            planIconId != null -> {
                Image(
                    painter = painterResource(id = planIconId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color(0x80EBEBEB),
                        modifier = Modifier
                            .size(90.dp)
                            .alpha(0.8f)
                    )

                    Text(
                        text = "Upload Photo",
                        color = Color(0x80EBEBEB),
                        modifier = Modifier.offset(y = (-8).dp),
                        fontFamily = rubikFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
        }

    }

}

@Preview
@Composable
private fun UploadPhotoCardPreview() {
    GymifyTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            UploadPhotoCard(
                onUploadImageClick = { }
            )
        }
    }
}