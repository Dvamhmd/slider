package com.example.dots.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dots.models.CheckoutData
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.repository.CheckoutRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.example.dots.models.BaseResponse
import com.example.dots.models.PlaceOrderResponse
class CheckOutViewModel(private val repository: CheckoutRepository) : ViewModel() {

      // Loading & Error
      private val _loading = MutableLiveData<Boolean>()
      val loading: LiveData<Boolean> = _loading

      private val _error = MutableLiveData<String?>()
      val error: LiveData<String?> = _error

      // Response prepareCheckout
      private val _checkoutResponse = MutableLiveData<CheckoutData?>()
      val checkoutResponse: LiveData<CheckoutData?> = _checkoutResponse

      // Response placeOrder
      private val _placeOrderResponse = MutableLiveData<BaseResponse<PlaceOrderResponse>>()
      val placeOrderResponse: LiveData<BaseResponse<PlaceOrderResponse>> = _placeOrderResponse


      fun prepareCheckout(tokoId: String, items: List<RequestCheckoutItem>, promoId: String? = null) {
            _loading.value = true
            viewModelScope.launch {
                  try {
                    val response = repository.prepareCheckout(tokoId, items, promoId)
                    _checkoutResponse.value = response.data
                  } catch (e: Exception) {
                    _error.value = e.message
                  } finally {
                    _loading.value = false
                  }
                }
          }

      fun placeOrder(
            tokoId: String,
        items: List<RequestCheckoutItem>,
        metodePengiriman: String,
        promoId: String? = null,
        idAlamat: String? = null,
        catatan: String? = null
      ) {
            _loading.value = true
            viewModelScope.launch {
                  try {
                    val result = repository.placeOrder(tokoId, items, metodePengiriman, promoId, idAlamat, catatan)
                    _placeOrderResponse.value = result
                  } catch (e: Exception) {
                    _error.value = e.message
                  } finally {
                    _loading.value = false
                  }
                }
          }
}

