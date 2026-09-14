package com.magsala.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagsalaApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagsalaApp() {
    val primary = Color(0xFF6750A4)
    val container = Color(0xFFFEAD0F)
    var selected by remember { mutableStateOf(0) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = primary,
            primaryContainer = container
        )
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("مغسلة هلال") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = container
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selected == 0,
                        onClick = { selected = 0 },
                        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                        label = { Text("الرئيسية") }
                    )
                    NavigationBarItem(
                        selected = selected == 1,
                        onClick = { selected = 1 },
                        icon = { Icon(Icons.Filled.List, contentDescription = null) },
                        label = { Text("طلباتي") }
                    )
                    NavigationBarItem(
                        selected = selected == 2,
                        onClick = { selected = 2 },
                        icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                        label = { Text("حسابي") }
                    )
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = if (selected == 0) "مرحبا بك في مغسلة هلال" else if (selected == 1) "طلباتك" else "حسابك",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}
