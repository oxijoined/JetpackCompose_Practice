package com.example.exam.classes

// Определение data класса Product.
// Data классы в Kotlin автоматически предоставляют функции equals(), hashCode(), toString() и copy().
data class Product(
    val id: Int, // Уникальный идентификатор продукта.
    val title: String, // Название продукта.
    val thumbnail: String // Ссылка на миниатюрное изображение продукта.
)

// Определение data класса ProductResponse.
// Этот класс используется для представления ответа от API, содержащего список продуктов.
data class ProductResponse(
    val products: List<Product> // Список продуктов, полученных от API.
)
