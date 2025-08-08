package com.example.gymify.settings.presentation.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.gymify.R
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily


@Composable
fun UserProfilePicture(
    modifier: Modifier = Modifier,
    profilePictureUri: String?,
    onEditClick: () -> Unit,
    userName: String?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(90.dp)
                .background(color = Color(0XFFEBEBEB), shape = CircleShape)
        ) {

            if (profilePictureUri != null) {
                AsyncImage(
                    model = profilePictureUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.icon_blank_pfp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                        .clip(CircleShape)
                )
            }

            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                    .clickable(onClick = onEditClick, role = Role.Button)
                    .padding(4.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = userName ?: "Gym Bro #1",
            color = Color(0XFFEBEBEB),
            fontSize = 18.sp,
            fontFamily = rubikFontFamily,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview
@Composable
private fun UserProfilePicturePreview() {
    GymifyTheme {
        UserProfilePicture(
            userName = "John Wick",
            profilePictureUri = null,
            onEditClick = { }
        )
    }
}