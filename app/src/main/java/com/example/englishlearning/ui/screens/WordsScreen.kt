
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
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.englishlearning.data.model.Word
import com.example.englishlearning.viewmodel.WordViewModel

@Composable
fun WordsScreen(
    onOpenWordDetail: (Word) -> Unit
) {
    val wordViewModel: WordViewModel = hiltViewModel()
    val wordList by wordViewModel.wordList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBarSection(query = searchQuery, onQueryChange = { searchQuery = it })
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
        ) {
            items(wordList) { word ->
                WordItem(word = word, onClick = { onOpenWordDetail(word) })
            }
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
        placeholder = { Text(text = "搜索单词...") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {}
}

@Composable
fun WordItem(word: Word, onClick: () -> Unit) {
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
                        .background(MaterialTheme.colorScheme.accent.copy(0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LibraryBooks,
                        contentDescription = word.word,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.accent
                    )
                }
                
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = word.word,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        if (!word.phonetic.isNullOrEmpty()) {
                            Text(
                                text = word.phonetic,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                    Text(
                        text = word.meaning,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.8f),
                        maxLines = 1
                    )
                }
            }
        }
    }
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(8.dp))
}
