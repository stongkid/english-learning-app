
package com.example.englishlearning.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishlearning.R

sealed class Screen(val route: String, val label: Int, val icon: androidx.compose.material.icons.Icon) {
    object Home : Screen("home", R.string.title_home, Icons.Default.Home)
    object Listening : Screen("listening", R.string.title_listening, Icons.Default.MusicNote)
    object Reading : Screen("reading", R.string.title_reading, Icons.Default.Book)
    object Words : Screen("words", R.string.title_words, Icons.Default.LibraryBooks)
    object Statistics : Screen("statistics", R.string.title_statistics, Icons.Default.TrendingUp)
}

@Composable
fun NavigationBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    val screens = listOf(
        Screen.Home,
        Screen.Listening,
        Screen.Reading,
        Screen.Words,
        Screen.Statistics
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                val isSelected = currentScreen.route == screen.route
                Box(
                    modifier = Modifier
                        .clickable { onNavigate(screen) }
                        .height(64.dp)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = stringResource(screen.label),
                            modifier = Modifier.size(24.dp),
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(0.6f)
                            }
                        )
                        Text(
                            text = stringResource(screen.label),
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                            color = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(0.6f)
                            }
                        )
                    }
                }
            }
        }
    }
}
