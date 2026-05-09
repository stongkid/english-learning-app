
package com.bainex.englishlearning.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bainex.englishlearning.viewmodel.WordViewModel

@Composable
fun StatisticsScreen() {
    val wordViewModel: WordViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderSection()
        
        StatsCards()
        
        LearningProgressSection(viewModel = wordViewModel)
        
        AchievementsSection()
    }
}

@Composable
private fun HeaderSection() {
    Text(
        text = "学习统计",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 24.dp)
    )
}

@Composable
private fun StatsCards() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatCard(
            icon = Icons.Default.AudioFile,
            label = "听力",
            value = "5小时",
            color = MaterialTheme.colorScheme.primary
        )
        StatCard(
            icon = Icons.Default.Book,
            label = "阅读",
            value = "20篇",
            color = MaterialTheme.colorScheme.secondary
        )
        StatCard(
            icon = Icons.Default.LibraryBooks,
            label = "单词",
            value = "320个",
            color = MaterialTheme.colorScheme.tertiary
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun RowScope.StatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(32.dp),
                tint = color
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 24.sp,
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
private fun LearningProgressSection(viewModel: WordViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "学习进度",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            ProgressBar(
                label = "CET4词汇",
                progress = 320,
                total = 4500,
                color = MaterialTheme.colorScheme.primary
            )
            
            ProgressBar(
                label = "已掌握",
                progress = 200,
                total = 320,
                color = MaterialTheme.colorScheme.secondary
            )
            
            ProgressBar(
                label = "复习中",
                progress = 120,
                total = 320,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun ProgressBar(label: String, progress: Int, total: Int, color: Color) {
    val percentage = if (total > 0) (progress.toFloat() / total.toFloat() * 100).toInt() else 0
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
            )
            Text(
                text = "$progress/$total ($percentage%)",
                fontSize = 14.sp,
                color = color
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray.copy(0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(percentage.toFloat() / 100)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun AchievementsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "学习成就",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AchievementItem(
                    icon = Icons.Default.CheckCircle,
                    title = "初学者",
                    description = "完成首次学习",
                    unlocked = true
                )
                AchievementItem(
                    icon = Icons.Default.TrendingUp,
                    title = "坚持7天",
                    description = "连续学习一周",
                    unlocked = true
                )
                AchievementItem(
                    icon = Icons.Default.LibraryBooks,
                    title = "词汇达人",
                    description = "学习100个单词",
                    unlocked = true
                )
                AchievementItem(
                    icon = Icons.Default.AudioFile,
                    title = "听力专家",
                    description = "累计听力10小时",
                    unlocked = false
                )
            }
        }
    }
}

@Composable
private fun AchievementItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    unlocked: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (unlocked) MaterialTheme.colorScheme.primary.copy(0.1f) else Color.Gray.copy(0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = if (unlocked) MaterialTheme.colorScheme.primary else Color.Gray.copy(0.5f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (unlocked) MaterialTheme.colorScheme.onBackground else Color.Gray.copy(0.5f)
        )
        Text(
            text = description,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
    }
}
