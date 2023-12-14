package com.example.exam.activities

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exam.classes.RetrofitInstance
import com.example.exam.classes.LoginResponse

// Определение Composable функции для экрана входа.
@Composable
fun LoginScreen(onLogin: () -> Unit) {
    // Использование remember для сохранения состояний полей ввода и индикатора загрузки.
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Column используется для вертикального расположения элементов.
    Column(modifier = Modifier.padding(16.dp)) {
        // Поле ввода для имени пользователя.
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        // Spacer создает пространство между элементами.
        Spacer(modifier = Modifier.height(8.dp))
        // Поле ввода для пароля.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Кнопка для входа.
        Button(
            onClick = {
                isLoading = true
                // Вызов API для входа.
                RetrofitInstance.api.login(username, password)
                    .enqueue(object : retrofit2.Callback<LoginResponse> {
                        override fun onResponse(
                            call: retrofit2.Call<LoginResponse>,
                            response: retrofit2.Response<LoginResponse>
                        ) {
                            if (response.isSuccessful) {
                                // Вызов колбэка onLogin при успешном входе.
                                onLogin()
                            } else {
                                // Логирование в случае ошибки.
                                Log.e("LoginScreen", "Неудачный ответ: ${response.code()}")
                            }
                            isLoading = false
                        }

                        override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                            isLoading = false
                        }
                    })
            },
            enabled = !isLoading
        ) {
            Text("Войти")
        }
        // Индикатор загрузки, отображается когда идет запрос на сервер.
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}
