package com.example.exam.classes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Объект RetrofitInstance - это синглтон, используемый для создания экземпляра ApiService.
object RetrofitInstance {
    // Использование 'by lazy' гарантирует, что объект ApiService будет инициализирован при его первом использовании.
    val api: ApiService by lazy {
        // Создание экземпляра Retrofit.
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/") // Указание базового URL для HTTP-запросов.
            .addConverterFactory(GsonConverterFactory.create()) // Добавление конвертера Gson для автоматической сериализации и десериализации данных JSON.
            .build() // Сборка экземпляра Retrofit.
            .create(ApiService::class.java) // Создание экземпляра ApiService из интерфейса, определенного пользователем.
    }
}
