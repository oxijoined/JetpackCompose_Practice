package com.example.exam.classes

// Data класс LoginResponse, используемый для представления ответа от сервера после запроса входа.
data class LoginResponse(
    val token: String, // Токен аутентификации, предоставляемый сервером после успешного входа.
)
