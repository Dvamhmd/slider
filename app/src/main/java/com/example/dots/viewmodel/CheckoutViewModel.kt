package com.example.dots.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dots.models.CheckoutData
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.repository.CheckoutRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.*

class CheckoutViewModel(private val repository: CheckoutRepository) : ViewModel() {

    val checkoutResponse = MutableLiveData<CheckoutData?>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    fun prepareCheckout(tokoId: String, items: List<RequestCheckoutItem>, promoId: String? = null) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.prepareCheckout(tokoId, items, promoId)
                checkoutResponse.value = response.data
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                loading.value = false
            }
        }
    }
}
