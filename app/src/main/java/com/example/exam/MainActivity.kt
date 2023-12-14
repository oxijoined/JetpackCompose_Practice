package com.example.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.exam.ui.theme.ExamTheme
import androidx.compose.runtime.*
import com.example.exam.activities.LoginScreen
import com.example.exam.activities.ProductScreen
import com.example.exam.classes.RetrofitInstance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Задает содержимое данной активности с использованием Jetpack Compose.
        setContent {
            // remember позволяет сохранить состояние переменной при пересоздании композиции.
            // showLogin будет хранить состояние отображения экрана входа.
            var showLogin by remember {
                mutableStateOf(true)
            }

            // ExamTheme - это пользовательская тема, определенная в проекте.
            ExamTheme {
                // Surface - контейнер с фоном, цвет которого соответствует теме приложения.
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    // Условный оператор для отображения экрана входа или основного содержимого.
                    if (showLogin) {
                        // Если showLogin true, показывается экран входа.
                        // onLogin - колбэк, изменяющий состояние showLogin.
                        LoginScreen(onLogin = { showLogin = false })
                    } else {
                        // В противном случае показывается экран продуктов.
                        // RetrofitInstance.api предоставляет доступ к API для этого экрана.
                        ProductScreen(apiService = RetrofitInstance.api)
                    }
                }
            }
        }
    }
}
