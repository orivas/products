package com.orivas.products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orivas.products.domain.NetworkResult
import com.orivas.products.domain.model.Product
import com.orivas.products.domain.model.Products
import com.orivas.products.domain.usecase.GetProductsUseCase
import com.orivas.products.domain.usecase.UseCase
import com.orivas.products.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val useCase: UseCase<GetProductsUseCase.Params, Products>
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Products>>(UiState.None)
    val uiState: StateFlow<UiState<Products>> = _uiState

    private fun updateState(newState: UiState<Products>) {
        _uiState.value = newState
    }

    private var currentPage = 1
    private var currentSearch = ""

    fun getProducts(search: String = currentSearch) {
        viewModelScope.launch {
            currentSearch = search
            val params = GetProductsUseCase.Params(currentSearch, currentPage)
            useCase.execute(params).collect { result ->
                when (result) {
                    is NetworkResult.OnSuccess -> {
                        updateState(
                            UiState.Success(
                                data = Products(
                                    products = addNewProducts(result.data.products)
                                )
                            )
                        )
                    }

                    is NetworkResult.OnError -> {
                        updateState(
                            UiState.Error(
                                result.exception.message.toString()
                            )
                        )
                    }

                    is NetworkResult.Loading -> {
                        updateState(UiState.Loading)
                    }
                }

            }
        }
    }

    private fun addNewProducts(products: List<Product>): List<Product> {
        return mutableListOf<Product>().apply {
            if (_uiState.value is UiState.Success<Products>) {
                addAll((_uiState.value as UiState.Success<Products>).data.products)
            }
            addAll(products)
        }
    }

    fun onLastItem() {
        currentPage++
        getProducts()
    }
}