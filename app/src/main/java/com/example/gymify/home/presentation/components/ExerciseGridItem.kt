package com.example.gymify.home.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import com.example.gymify.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay

@Composable
fun ExerciseGridItem(
    modifier: Modifier = Modifier,
    @StringRes exerciseName: Int,
    @DrawableRes iconFirst: Int,
    @DrawableRes iconSecond: Int? = null,
    onClick: () -> Unit,
    backgroundShape: Boolean = false,
) {
    // Добавляем состояние для отслеживания текущей иконки
    var currentIcon by remember { mutableIntStateOf(iconFirst) }

    // Запускаем эффект для переключения иконок каждую секунду
    LaunchedEffect(key1 = iconFirst, key2 = iconSecond) {
        // Если вторая иконка null, нет необходимости менять изображение
        if (iconSecond != null) {
            while (true) {
                delay(2500)
                currentIcon = if (currentIcon == iconFirst) iconSecond else iconFirst
            }
        }
    }


    Box(
        modifier = modifier
            .size(175.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick, role = Role.Button)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = stringResource(exerciseName),
                fontSize = 12.sp,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFEBEBEB)
            )

//            Spacer(Modifier.height(5.dp))


            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter =  painterResource(R.drawable.form_ellipse_2)
                 ,
                    modifier = modifier
                        .align(Alignment.Center)
                        .size(105.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
                Image(
                    painter = painterResource(currentIcon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(70.dp)
                )

            }

            Spacer(Modifier.height(4.dp))

            AddExerciseButton(onClick = onClick)


        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AddExerciseButtonZaza(
    modifier: Modifier = Modifier,
) {
    GymifyTheme {
        ExerciseGridItem(
            exerciseName = R.string.muscle_shoulders,
            iconFirst = R.drawable.icon_deadlift_man,
            iconSecond = R.drawable.flag_azerbaijan,
            onClick = { },
            backgroundShape = true

        )
    }

}

@Preview
@Composable
private fun AddExerciseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    GymifyTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .height(20.dp)
                .aspectRatio(3.2f)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable(onClick = onClick, role = Role.Button)
        ) {
            Image(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }

}

