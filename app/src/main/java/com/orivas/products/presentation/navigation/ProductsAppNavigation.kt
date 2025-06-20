package com.orivas.products.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orivas.products.R
import com.orivas.products.presentation.screens.ListProductsScreen
import com.orivas.products.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductsNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    productsViewModel: ProductsViewModel
    ) {

    val initialRoute = stringResource(R.string.initial_route)
    NavHost(navController = navController, startDestination = initialRoute) {
        composable(initialRoute) {
            ListProductsScreen(
                modifier = modifier,
                productsViewModel = productsViewModel
            )
        }
    }
}