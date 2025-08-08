package com.example.gymify.core.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.R
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.analytics.navigation.Analytics
import com.example.gymify.home.navigation.Home
import com.example.gymify.settings.navigation.Settings
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedDestination: NavigationDestination? = Home,
    onNavigate: (NavigationDestination) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(26.dp)
            )
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        bottomNavItems.forEach {
            NavigationBarItem(
                isSelected = selectedDestination?.section == it.destination.section,
                bottomNavItem = it,
                onClick = { onNavigate(it.destination) }
            )
        }
    }
}

@Composable
fun NavigationBarItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    bottomNavItem: BottomNavItem,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) {
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    Color(0xFF617BB6)
                )
            ),
            shape = RoundedCornerShape(12.dp)
        )
    } else Modifier.background(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp)
    )


    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .then(backgroundColor)
            .clickable(
                onClick = onClick,
                role = Role.Button,
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(bottomNavItem.icon),
            contentDescription = bottomNavItem.contentDescription,
            tint = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
    }
}

private val bottomNavItems = listOf(
    BottomNavItem(
        icon = R.drawable.icon_nav_home,
        destination = Home,
        contentDescription = "Home Screen"
    ),
    BottomNavItem(
        icon = R.drawable.icon_nav_analytics,
        destination = Analytics,
        contentDescription = "Analytics"
    ),
    BottomNavItem(
        icon = R.drawable.icon_nav_settings,
        destination = Settings,
        contentDescription = "Profile"
    ),
)

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val destination: NavigationDestination,
    val contentDescription: String,
)

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    GymifyTheme {
        BottomNavigationBar(selectedDestination = Home, onNavigate = { })
    }

}
