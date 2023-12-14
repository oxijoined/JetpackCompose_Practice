package com.example.exam.classes

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.Call

// Определение интерфейса ApiService для описания сетевых запросов к REST API.
interface ApiService {
    // Определение GET-запроса для получения списка продуктов.
    @GET("products")
    fun getProducts(): Call<ProductResponse>

    // Определение POST-запроса для аутентификации пользователя.
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("username") username: String, // Поле формы для имени пользователя.
        @Field("password") password: String // Поле формы для пароля.
    ): Call<LoginResponse> // Ожидаемый ответ - LoginResponse.
}
