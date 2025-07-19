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
import com.example.dots.models.Transaksi
import android.util.Log

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

    private val _snapToken = MutableLiveData<String>()
    val snapToken: LiveData<String> = _snapToken

    private val _orders = MutableLiveData<List<Transaksi>>()
    val orders: LiveData<List<Transaksi>> = _orders

    private val _errorType = MutableLiveData<String?>()
    val errorType: LiveData<String?> = _errorType

    private val _itemBermasalah = MutableLiveData<String?>()
    val itemBermasalah: LiveData<String?> = _itemBermasalah




    fun prepareCheckout(tokoId: String, items: List<RequestCheckoutItem>, promoId: String? = null) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.prepareCheckout(tokoId, items, promoId)

                if (!response.message.isNullOrEmpty() && response.errorType.isNullOrEmpty()) {
                    Log.i("response true", "response true")
                    _checkoutResponse.value = response.data
                    _error.value = null
                    _errorType.value = null
                    _itemBermasalah.value = null
                } else {
                    Log.i("response eror", "response eror")
                    _checkoutResponse.value = null // <=== penting!
                    _error.value = response.message
                    _errorType.value = response.errorType
                    _itemBermasalah.value = response.itemBermasalah
                }


            } catch (e: Exception) {
                _checkoutResponse.value = null // <=== penting!
                _error.value = e.message
                Log.e("prepareCheckout", "Exception: ${e.message}", e)
            }
            finally {
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

    fun getOrderHistory() {
        viewModelScope.launch {
            try {
                val response = repository.getOrderHistory()
                if (response.isSuccessful) {
                    _orders.value = response.body()?.data ?: emptyList()
                } else if (response.code() == 404) {
                    // Tangani tidak ada pesanan sebagai list kosong
                    _orders.value = emptyList()
                } else {
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Terjadi kesalahan"
            }
        }
    }



    fun getSnapToken(idTransaksi: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = repository.getSnapToken(idTransaksi)
                if (response.isSuccessful && response.body() != null) {
                    android.util.Log.d("snaptoken", "tokennya: ${response.body()!!.token}")
                    _snapToken.value = response.body()!!.token!!
                } else {
                    Log.e("SnapToken", "Gagal: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("SnapToken", "Error: ${e.message}", e)
            } finally {
                _loading.value = false
            }
        }
    }

}

