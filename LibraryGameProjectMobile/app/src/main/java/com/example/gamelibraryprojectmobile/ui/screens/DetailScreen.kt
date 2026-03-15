package com.example.gamelibrary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamelibrary.data.MockData
import com.example.gamelibrary.ui.theme.SteamColors



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(gameId: Int, onBack: () -> Unit) {
    val game = MockData.games.find { it.id == gameId } ?: MockData.games.first()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        game.title,
                        color = SteamColors.TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                            tint = SteamColors.TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SteamColors.Surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SteamColors.Background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            //Изображение игры с подсветкой
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SteamColors.SurfaceLight)
            ) {
                Image(
                    painter = painterResource(id = game.imageResId),
                    contentDescription = game.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Название игры
            Text(
                text = game.title,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = SteamColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Жанр
            Surface(
                color = SteamColors.SurfaceLight,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = game.genre,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = SteamColors.Primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Рейтинг
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(SteamColors.Surface, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Рейтинг ",
                    fontSize = 18.sp,
                    color = SteamColors.Rating
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = game.rating.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = SteamColors.Rating
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Описание
            Card(
                colors = CardDefaults.cardColors(containerColor = SteamColors.Surface),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Описание",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = SteamColors.Primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = game.description,
                        fontSize = 15.sp,
                        color = SteamColors.TextSecondary,
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Дополнительная кнопка
            OutlinedButton(
                onClick = { /* пустышка */ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = SteamColors.Primary
                ),
                border = BorderStroke(1.dp, SteamColors.Primary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Запустить",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}