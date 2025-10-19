package com.example.app

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import com.example.app.R
import com.example.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            AppTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ThemeSwitch(isDarkTheme) { isDarkTheme = it }
                        UserForm()
                    }
                }
            }
        }
    }
}


@Composable
fun UserForm() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(25) }
    var gender by remember { mutableStateOf("Мужчина") }
    var news by remember { mutableStateOf(false) }
    var show by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Возраст
        Text(text = stringResource(R.string.age, age))
        Slider(
            value = age.toFloat(),
            onValueChange = { age = it.toInt() },
            valueRange = 1f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Пол
        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = gender == "Мужчина",
                    onClick = { gender = "Мужчина" }
                )
                Text(stringResource(R.string.male))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = gender == "Женщина",
                    onClick = { gender = "Женщина" }
                )
                Text(stringResource(R.string.female))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Новости
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = news,
                onCheckedChange = { news = it }
            )
            Text(stringResource(R.string.news))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка
        Button(
            onClick = { show = true },
            enabled = name.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.send))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Результат
        if (show) {
            Spacer(modifier = Modifier.height(24.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Имя: $name")
                    Text("Возраст: $age")
                    Text("Пол: $gender")
                    Text("Подписка: ${if (news) "да" else "нет"}")
                }
            }
        }
    }
}

@Composable
fun ThemeSwitch(isDarkTheme: Boolean, onToggle: (Boolean) -> Unit) {
    Button(
        onClick = { onToggle(!isDarkTheme) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(if (isDarkTheme) "Светлая тема" else "Тёмная тема")
    }
}
@Preview
@Composable
fun PreviewForm() {
    UserForm()
}