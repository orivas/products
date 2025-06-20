package com.orivas.products.data.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("plpResults")
    val results: PlpResult
)

data class PlpResult(
    @SerializedName("records")
    val products: List<ProductResponse>
)

data class ProductResponse(
    @SerializedName("productId")
    val productId: Long? = 0,
    @SerializedName("productDisplayName")
    val name: String = "",
    @SerializedName("listPrice")
    val price: Float = 0.0f,
    @SerializedName("promoPrice")
    val promoPrice: Float = 0.0f,
    @SerializedName("smImage")
    val image: String = "",
    @SerializedName("variantsColor")
    val colors: List<VariantColorResponse>? = null
)

data class VariantColorResponse(
    val colorName: String?,
    val colorHex: String?,
    val colorImageURL: String?,
    val colorMainURL: String?,
    val skuId: String?,
    val galleryImages: String?
)
