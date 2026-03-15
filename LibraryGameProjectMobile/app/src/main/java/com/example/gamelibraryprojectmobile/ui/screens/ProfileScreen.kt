package com.example.gamelibrary.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gamelibrary.R
import com.example.gamelibrary.navigation.Screen

@Composable
fun ProfileScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    // Состояние для 5 галочек
    val tasks = remember { mutableStateListOf(false, false, false, false, false) }
    val completedCount = tasks.count { it }
    val progress = completedCount / 5f
    val isRewardUnlocked = progress >= 1f

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Выход") },
            text = { Text("Вы точно хотите выйти из профиля?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                }) { Text("Да") }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDialog = false }) { Text("Нет") }
            }
        )
    }

    //  скролл
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),  // 🔥 ВОТ ЭТО ДЕЛАЕТ СКРОЛЛ!
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Аватарка
        Image(
            painter = painterResource(id = R.drawable.eye),
            contentDescription = "Аватар пользователя",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Danilka",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Доп. инфа • Крутышка я",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // шкала для награды
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Крутая награда",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$completedCount/5",
                        fontSize = 16.sp,
                        color = Color(0xFF6200EE)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Прогресс бар
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    color = Color(0xFF6200EE),
                    trackColor = Color(0xFFE0E0E0)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Для получения подарка выполните все задания",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${(progress * 100).toInt()}% выполнено",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 5 галочек
        Text(
            text = "Задания",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks.indices.toList()) { index ->
                TaskCheckbox(
                    isCompleted = tasks[index],
                    onToggle = { tasks[index] = !tasks[index] }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Награда
        AnimatedVisibility(
            visible = isRewardUnlocked,
            enter = fadeIn(animationSpec = tween(500)) + scaleIn(initialScale = 0.8f)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF9C4)
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, Color(0xFFFFC107)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Твоя награда!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EE)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Все задания выполнены!",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    //картинка награды
                    Image(
                        painter = painterResource(id = R.drawable.tuma),
                        contentDescription = "Награда",
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "!!!!!Красава!!!!!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF4CAF50)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        // Карточки статистики
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatCard("Игр", "100")
            StatCard("Часов", "5k+")
            StatCard("Достижений", "10k+")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // выход
        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Выйти из профиля",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Компонент одной галочки
@Composable
fun TaskCheckbox(isCompleted: Boolean, onToggle: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(70.dp)
            .clickable { onToggle() }
    ) {
        Icon(
            imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.Circle,
            contentDescription = if (isCompleted) "Выполнено" else "Не выполнено",
            tint = if (isCompleted) Color(0xFF4CAF50) else Color(0xFFBDBDBD),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Задание",
            fontSize = 12.sp,
            color = Color.Gray,
            maxLines = 1
        )
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}