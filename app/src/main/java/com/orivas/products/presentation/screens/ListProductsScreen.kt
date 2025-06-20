package com.orivas.products.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.orivas.products.domain.model.Products
import com.orivas.products.presentation.model.UiState
import com.orivas.products.presentation.ui.EmptyData
import com.orivas.products.presentation.ui.ProductCardItem
import com.orivas.products.presentation.viewmodel.ProductsViewModel

@Composable
fun ListProductsScreen(
    modifier: Modifier = Modifier,
    productsViewModel: ProductsViewModel
) {

    val search by remember { mutableStateOf("") }
    val uiState = productsViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getProducts("ropa")
    }

    when (uiState.value) {
        is UiState.Loading -> {
            CircularProgressIndicator()
        }

        is UiState.Success -> {
            ListProducts(
                modifier = modifier,
                products = (uiState.value as UiState.Success<Products>).data,
                search = search,
                onValueChange = {
                    productsViewModel.getProducts(search = it)
                }
            )
        }

        is UiState.Error -> {
            EmptyData(modifier = modifier)
        }

        UiState.None -> { /*Nothing to do*/
        }
    }

}

@Composable
fun ListProducts(
    modifier: Modifier = Modifier,
    products: Products,
    search: String = "",
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            label = { Text("Search") },
            onValueChange = {
                onValueChange.invoke(it)
            }
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
        ) {

            items(products.products) { product ->
                ProductCardItem(
                    modifier = modifier,
                    product = product
                )
            }
        }
    }
}