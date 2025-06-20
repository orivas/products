package com.orivas.products.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.orivas.products.domain.model.Product
import com.orivas.products.domain.model.VariantColor
import com.orivas.products.presentation.utils.toFormatPrice

@Composable
fun ProductCardItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
            .background(
                Color.White, shape = RoundedCornerShape(topEndPercent = 10, bottomEndPercent = 10)
            )
        ,
        verticalAlignment = Alignment.Bottom
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .clip(CircleShape.copy(topStart = CornerSize(10.dp), bottomStart = CornerSize(10.dp))),
            contentScale = ContentScale.Crop,
            model = product.image,
            contentDescription = "product image"
        )
        Column(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = product.name, color = Color.Black, fontWeight = FontWeight.SemiBold)
            Text(text = product.price.toFormatPrice(), color = Color.Gray, style = TextStyle(
                textDecoration = TextDecoration.LineThrough
            ))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = product.priceWithDiscount.toFormatPrice(), color = Color.Red)
            Spacer(modifier = Modifier.height(10.dp))
            VariantColors(product.colors)
        }
    }
}