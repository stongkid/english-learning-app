
package com.example.englishlearning.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.englishlearning.data.model.Reading
import com.example.englishlearning.viewmodel.ReadingViewModel

@Composable
fun ReadingScreen(
    onOpenReading: (Reading) -> Unit,
    onImportReading: () -> Unit
) {
    val readingViewModel: ReadingViewModel = hiltViewModel()
    val readingList by readingViewModel.readingList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBarSection(query = searchQuery, onQueryChange = { searchQuery = it })
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
        ) {
            items(readingList) { reading ->
                ReadingItem(reading = reading, onClick = { onOpenReading(reading) })
            }
        }

        FloatingActionButton(
            onClick = onImportReading,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Import",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSection(query: String, onQueryChange: (String) -> Unit) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        placeholder = { Text(text = "搜索阅读材料...") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {}
}

@Composable
fun ReadingItem(reading: Reading, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.secondary.copy(0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = reading.title ?: "Reading",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(
                        text = reading.title ?: "未命名文章",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${getSourceTypeLabel(reading.sourceType)} | 进度：${reading.progress}%",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                    )
                }
            }
        }
    }
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(8.dp))
}

fun getSourceTypeLabel(sourceType: Int): String {
    return when (sourceType) {
        Reading.SOURCE_CAMERA -> "拍照"
        Reading.SOURCE_PDF -> "PDF"
        Reading.SOURCE_TEXT -> "文本"
        else -> "其他"
    }
}
