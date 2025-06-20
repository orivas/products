package com.orivas.products.domain.model

data class Products(
    val products: List<Product>
)

data class Product(
    val name: String,
    val price: Float,
    val priceWithDiscount: Float,
    val image: String,
    val colors: List<VariantColor> = listOf()
)

data class VariantColor(
    val colorName: String?,
    val colorHex: String?,
    val colorImageURL: String? = null,
    val colorMainURL: String? = null,
    val skuId: String? = null,
    val galleryImages: String? = null
)