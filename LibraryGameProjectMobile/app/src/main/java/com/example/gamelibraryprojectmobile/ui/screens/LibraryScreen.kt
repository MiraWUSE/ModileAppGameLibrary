package com.example.gamelibrary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamelibrary.data.MockData
import com.example.gamelibrary.ui.theme.SteamColors


@Composable
fun LibraryScreen(onGameClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SteamColors.Background)
            .padding(16.dp)
    ) {
        Text(
            text = "Моя библиотека",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = SteamColors.TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${MockData.games.size} игр в библиотеке",
            fontSize = 14.sp,
            color = SteamColors.TextSecondary
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(MockData.games) { game ->
                LibraryGameCard(game = game, onClick = { onGameClick(game.id) })
            }
        }
    }
}

@Composable
fun LibraryGameCard(game: com.example.gamelibrary.model.Game, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = SteamColors.Surface),  // 🎨 Тёмная карточка
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Изображение игры
            Image(
                painter = painterResource(id = game.imageResId),
                contentDescription = game.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = game.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = SteamColors.TextPrimary,
                textAlign = TextAlign.Center,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = game.genre,
                fontSize = 12.sp,
                color = SteamColors.TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}