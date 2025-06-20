package com.orivas.products.data.mappers

import com.orivas.products.data.model.ProductResponse
import com.orivas.products.data.model.ProductsResponse
import com.orivas.products.data.model.VariantColorResponse
import com.orivas.products.domain.model.Product
import com.orivas.products.domain.model.Products
import com.orivas.products.domain.model.VariantColor

fun ProductsResponse.toDomain(): Products {
    return Products(
        products = this.results.products.map { it.toDomain() }
    )
}

fun VariantColorResponse.toDomain(): VariantColor {
    return VariantColor(
        colorName = this.colorName,
        colorHex = this.colorHex
    )
}

fun ProductResponse.toDomain(): Product {
    return Product(
        name = this.name,
        price = this.price,
        priceWithDiscount = this.promoPrice,
        image = this.image,
        colors = this.colors?.map { it.toDomain() } ?: listOf()
    )
}