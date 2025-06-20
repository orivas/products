package com.orivas.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.orivas.products.presentation.navigation.ProductsNavigation
import com.orivas.products.presentation.ui.theme.BackgroundColor
import com.orivas.products.presentation.ui.theme.ProductsTheme
import com.orivas.products.presentation.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productsViewModel:ProductsViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            ProductsTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = BackgroundColor,
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Products") }
                        )
                    }
                ) { innerPadding ->

                    ProductsNavigation(
                        Modifier.padding(innerPadding),
                        navController = navController,
                        productsViewModel = productsViewModel
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductsTheme {

    }
}