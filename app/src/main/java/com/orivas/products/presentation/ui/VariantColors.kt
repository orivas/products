package com.orivas.products.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.orivas.products.domain.model.VariantColor

@Composable
fun VariantColors(variants: List<VariantColor>) {
    LazyRow {
        items(variants) { variant ->
            VariantColorsItem(variant)
        }
    }
}

@Composable
fun VariantColorsItem(variantColor: VariantColor) {
    variantColor.colorHex?.let {
        Box(
            modifier = Modifier
                .padding(all = 2.dp)
                .size(15.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape.copy(all = CornerSize(15.dp)),
               )
                .clip(CircleShape.copy(all = CornerSize(15.dp)))
                .background(Color(variantColor.colorHex.replace("#", "FF").toLong(16)))
        )
    }
}