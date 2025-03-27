package com.example.gymify.sign_up.presentation.welcome_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.sign_up.presentation.components.ContinueButton
import com.example.gymify.ui.theme.goldmanFontFamily
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToGenderScreen: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF151517))
    ) {

        // Верхняя часть с текстами
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.welcome_title),
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 44.sp,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.app_name_title), fontFamily = goldmanFontFamily, fontSize = 44.sp,
                color = Color(0xFF4666B1)
            )
        }

        // Box с изображениями, расположенный по центру
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ellipse_1),
                contentDescription = null,
                modifier = Modifier
                    .size(340.dp)
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.deadlift_man),
                contentDescription = null,
                modifier = Modifier
                    .size(240.dp)
                    .align(Alignment.Center)
            )
        }

        // Кнопка внизу с маленьким отступом
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 56.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContinueButton(text = stringResource(R.string.welcome_start_button)) {
               onNavigateToGenderScreen()
            }
        }
    }
}