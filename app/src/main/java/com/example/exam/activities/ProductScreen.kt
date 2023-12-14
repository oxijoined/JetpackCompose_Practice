package com.example.exam.activities

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.glide.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.exam.classes.ProductResponse
import com.example.exam.classes.Product
import com.example.exam.classes.ApiService

// Определение Composable функции для отображения экрана с продуктами.
@Composable
fun ProductScreen(apiService: ApiService) {
    // remember позволяет сохранить состояние списка продуктов при пересоздании композиции.
    val products = remember { mutableStateOf(emptyList<Product>()) }

    // LaunchedEffect используется для выполнения кода с побочными эффектами, в данном случае - запроса к API.
    LaunchedEffect(Unit) {
        // Запрос к API для получения списка продуктов.
        apiService.getProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                // Проверка на успешный ответ от сервера.
                if (response.isSuccessful) {
                    // Обновление списка продуктов, если ответ успешный.
                    products.value = response.body()?.products ?: emptyList()
                } else {
                    // Логирование в случае неудачного ответа.
                    Log.e("ProductScreen", "Неудачный ответ: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                // Логирование в случае ошибки при запросе.
                Log.e("ProductScreen", "Ошибка загрузки продуктов: ", t)
            }
        })
    }

    // LazyColumn используется для эффективного отображения списков.
    LazyColumn {
        // items используется для отображения каждого элемента списка продуктов.
        items(products.value) { product ->
            // Card - виджет для отображения информации в виде карточки.
            Card {
                // GlideImage используется для загрузки и отображения изображений (в данном случае, миниатюр продуктов).
                GlideImage(
                    imageModel = product.thumbnail,
                    contentScale = ContentScale.Crop // Масштабирование содержимого изображения.
                )
                // Text - виджет для отображения текста, здесь для заголовка продукта.
                Text(product.title)
            }
        }
    }
}
