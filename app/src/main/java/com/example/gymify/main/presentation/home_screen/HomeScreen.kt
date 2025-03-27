package com.example.gymify.main.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.core.presentation.navigation.home.Home
import com.example.gymify.main.presentation.components.IconTopBar
import com.example.gymify.main.presentation.home_screen.components.YourWorkoutPlans
import com.example.gymify.ui.theme.GymifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavigationDestination) -> Unit,
    selectedDestination: NavigationDestination,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier,
        topBar = {
            IconTopBar(
                title = "Home",
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigate = { onNavigate(it) },
                selectedDestination = selectedDestination,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
        }


    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Spacer(Modifier.height(10.dp))

            YourWorkoutPlans(onAddWorkoutClick = { }, modifier = Modifier.padding(horizontal = 12.dp))
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    GymifyTheme {
        HomeScreen(
            onNavigate = { },
            selectedDestination = Home
        )
    }
}