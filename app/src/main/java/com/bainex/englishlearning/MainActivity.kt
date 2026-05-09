
package com.bainex.englishlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bainex.englishlearning.data.model.Audio
import com.bainex.englishlearning.data.model.Reading
import com.bainex.englishlearning.data.model.Word
import com.bainex.englishlearning.ui.components.NavigationBar
import com.bainex.englishlearning.ui.components.Screen
import com.bainex.englishlearning.ui.screens.HomeScreen
import com.bainex.englishlearning.ui.screens.ListeningScreen
import com.bainex.englishlearning.ui.screens.ReadingScreen
import com.bainex.englishlearning.ui.screens.StatisticsScreen
import com.bainex.englishlearning.ui.screens.WordsScreen
import com.bainex.englishlearning.ui.theme.EnglishLearningTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishLearningTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen: Screen by remember { mutableStateOf(Screen.Home) }
    val context = LocalContext.current

    fun handleNavigate(screen: Screen) {
        currentScreen = screen
    }

    fun handlePlayAudio(audio: Audio) {
        currentScreen = Screen.Listening
    }

    fun handleOpenReading(reading: Reading) {
        currentScreen = Screen.Reading
    }

    fun handleOpenWordDetail(word: Word) {
        currentScreen = Screen.Words
    }

    fun handleImportAudio() {
    }

    fun handleImportReading() {
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (currentScreen) {
            Screen.Home -> HomeScreen(
                onNavigateToListening = { handleNavigate(Screen.Listening) },
                onNavigateToReading = { handleNavigate(Screen.Reading) },
                onNavigateToWords = { handleNavigate(Screen.Words) },
                onNavigateToStatistics = { handleNavigate(Screen.Statistics) },
                onPlayAudio = { handlePlayAudio(it) },
                onOpenReading = { handleOpenReading(it) }
            )
            Screen.Listening -> ListeningScreen(
                onPlayAudio = { handlePlayAudio(it) },
                onImportAudio = { handleImportAudio() }
            )
            Screen.Reading -> ReadingScreen(
                onOpenReading = { handleOpenReading(it) },
                onImportReading = { handleImportReading() }
            )
            Screen.Words -> WordsScreen(
                onOpenWordDetail = { handleOpenWordDetail(it) }
            )
            Screen.Statistics -> StatisticsScreen()
        }

        NavigationBar(
            currentScreen = currentScreen,
            onNavigate = { handleNavigate(it) }
        )
    }
}
