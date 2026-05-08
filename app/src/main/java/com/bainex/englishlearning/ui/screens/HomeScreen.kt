
package com.bainex.englishlearning.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bainex.englishlearning.R
import com.bainex.englishlearning.data.model.Audio
import com.bainex.englishlearning.data.model.Reading
import com.bainex.englishlearning.viewmodel.AudioViewModel
import com.bainex.englishlearning.viewmodel.ReadingViewModel
import com.bainex.englishlearning.viewmodel.WordViewModel

@Composable
fun HomeScreen(
    onNavigateToListening: () -> Unit,
    onNavigateToReading: () -> Unit,
    onNavigateToWords: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onPlayAudio: (Audio) -> Unit,
    onOpenReading: (Reading) -> Unit
) {
    val audioViewModel: AudioViewModel = hiltViewModel()
    val readingViewModel: ReadingViewModel = hiltViewModel()
    val wordViewModel: WordViewModel = hiltViewModel()

    val audioList by audioViewModel.audioList.collectAsState()
    val readingList by readingViewModel.readingList.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
    ) {
        item {
            HeaderSection()
        }

        item {
            StatsSection(wordViewModel)
        }

        item {
            QuickActionsSection(
                onListening = onNavigateToListening,
                onReading = onNavigateToReading,
                onWords = onNavigateToWords,
                onStatistics = onNavigateToStatistics
            )
        }

        item {
            TodayGoalSection()
        }

        item {
            RecentLearningSection(
                audioList = audioList.take(2),
                readingList = readingList.take(2),
                onPlayAudio = onPlayAudio,
                onOpenReading = onOpenReading
            )
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "鑻辫瀛︿範鍔╂墜",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "姣忓ぉ杩涙涓€鐐圭偣",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
        }
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "User",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                tint = Color.White
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun StatsSection(viewModel: WordViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatCard(
            icon = Icons.Default.MusicNote,
            label = "鍚姏",
            value = "30min",
            color = MaterialTheme.colorScheme.primary
        )
        StatCard(
            icon = Icons.Default.Book,
            label = "闃呰",
            value = "2绡?,
            color = MaterialTheme.colorScheme.secondary
        )
        StatCard(
            icon = Icons.Default.Star,
            label = "鍗曡瘝",
            value = "50涓?,
            color = MaterialTheme.colorScheme.accent
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun StatCard(
    icon: androidx.compose.material.icons.Icon,
    label: String,
    value: String,
    color: Color
) {
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(0.1f))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
        }
    }
    Spacer(modifier = Modifier.width(12.dp))
}

@Composable
fun QuickActionsSection(
    onListening: () -> Unit,
    onReading: () -> Unit,
    onWords: () -> Unit,
    onStatistics: () -> Unit
) {
    Text(
        text = "蹇嵎鍏ュ彛",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 12.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionCard(
            icon = Icons.Default.MusicNote,
            label = "鍚姏",
            color = MaterialTheme.colorScheme.primary,
            onClick = onListening
        )
        ActionCard(
            icon = Icons.Default.Book,
            label = "闃呰",
            color = MaterialTheme.colorScheme.secondary,
            onClick = onReading
        )
        ActionCard(
            icon = Icons.Default.Star,
            label = "鍗曡瘝",
            color = MaterialTheme.colorScheme.accent,
            onClick = onWords
        )
        ActionCard(
            icon = Icons.Default.Star,
            label = "缁熻",
            color = MaterialTheme.colorScheme.error,
            onClick = onStatistics
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun ActionCard(
    icon: androidx.compose.material.icons.Icon,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .weight(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier.size(24.dp),
                    tint = color
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun TodayGoalSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "浠婃棩鐩爣",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            GoalItem(text = "鍚姏缁冧範 1绡?, completed = true)
            GoalItem(text = "闃呰缁冧範 1绡?, completed = false)
            GoalItem(text = "澶嶄範鍗曡瘝 10涓?, completed = false)
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun GoalItem(text: String, completed: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (completed) MaterialTheme.colorScheme.primary else Color.Gray.copy(0.3f)),
                contentAlignment = Alignment.Center
            ) {
                if (completed) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Completed",
                        modifier = Modifier.size(12.dp),
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun RecentLearningSection(
    audioList: List<Audio>,
    readingList: List<Reading>,
    onPlayAudio: (Audio) -> Unit,
    onOpenReading: (Reading) -> Unit
) {
    Text(
        text = "鏈€杩戝涔?,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 12.dp)
    )

    audioList.forEach { audio ->
        RecentItem(
            title = audio.name,
            subtitle = "涓婃瀛︿範锛?0鍒嗛挓鍓?,
            icon = Icons.Default.MusicNote,
            onClick = { onPlayAudio(audio) }
        )
    }

    readingList.forEach { reading ->
        RecentItem(
            title = reading.title ?: "鏈懡鍚嶆枃绔?,
            subtitle = "闃呰杩涘害锛?{reading.progress}%",
            icon = Icons.Default.Book,
            onClick = { onOpenReading(reading) }
        )
    }
}

@Composable
fun RecentItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.material.icons.Icon,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                )
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Continue",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onBackground.copy(0.4f)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}
