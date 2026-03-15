package com.example.gamelibrary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamelibrary.data.MockData
import com.example.gamelibrary.ui.theme.SteamColors



@Composable
fun HomeScreen(onGameClick: (Int) -> Unit, onSearchClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SteamColors.Background)
    ) {
        // Заголовок
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SteamColors.Surface)
                .padding(24.dp)
        ) {
            Text(
                text = "Магазин игр",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = SteamColors.TextPrimary
            )
            Text(
                text = "Покупайте игры)",
                fontSize = 16.sp,
                color = SteamColors.TextSecondary.copy(alpha = 0.8f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                // Кнопка поиска
                Card(
                    onClick = onSearchClick,
                    colors = CardDefaults.cardColors(containerColor = SteamColors.Surface),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Поиск",
                            tint = SteamColors.Primary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Поиск игр...",
                            fontSize = 16.sp,
                            color = SteamColors.TextSecondary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Популярное",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = SteamColors.TextPrimary,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(MockData.games) { game ->
                GameCard(game = game, onClick = { onGameClick(game.id) })
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun GameCard(game: com.example.gamelibrary.model.Game, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = SteamColors.Surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Изображение игры
            Image(
                painter = painterResource(id = game.imageResId),
                contentDescription = game.title,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Информация об игре
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = game.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = SteamColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = game.genre,
                    fontSize = 14.sp,
                    color = SteamColors.TextSecondary
                )
            }

            // Рейтинг
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Рейтинг",
                    tint = SteamColors.Rating,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = game.rating.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SteamColors.Rating
                )
            }
        }
    }
}