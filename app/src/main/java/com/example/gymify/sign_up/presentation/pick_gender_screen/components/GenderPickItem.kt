package com.example.gymify.sign_up.presentation.pick_gender_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable()
fun GenderPickItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int? = null,
    isSelected: Boolean = false,
    onGenderClick: () -> Unit
) {
    val iconBackgroundColor by animateColorAsState(
        targetValue = when {
            isSelected -> {
                when (text) {
                    stringResource(R.string.gender_female) -> Color(0xFFB04671)
                    stringResource(R.string.gender_male) -> Color(0xFF4666B1)
                    stringResource(R.string.gender_other) -> Color(0xFF3B4051)
                    else -> Color(0xFF1F1F1F)     // Стандартный цвет, если текст не совпадает
                }
            }
            else -> Color(0xFF1F1F1F) // Стандартный цвет, если не выбрано
        },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing) // Плавная анимация за 300 миллисекунд
    )

    Box(
        modifier = modifier
            .size(154.dp)
            .background(color = iconBackgroundColor, shape = CircleShape)
            .clip(shape = CircleShape)
            .clickable(onClick = onGenderClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .width(60.dp)
                        .height(75.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = text,
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 16.sp,
                )
            } else {
                Text(
                    text = text,
                    fontFamily = rubikFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 18.sp
                )

            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun GenderPickBoxPreview() {
    GymifyTheme {
        GenderPickItem(
            text = "Female",
            icon = R.drawable.female_symbol,
            onGenderClick = { }
        )
    }
}

@Preview(apiLevel = 34)
@Composable
private fun GenderPickBoxPreview2() {
    GymifyTheme {
        GenderPickItem(
            text = "Male",
            icon = R.drawable.male_symbol,
            onGenderClick = { }
        )
    }
}
