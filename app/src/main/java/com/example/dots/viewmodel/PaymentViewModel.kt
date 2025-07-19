package com.example.dots.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dots.network.ApiService
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dots.models.LoginResponse
import com.example.dots.repository.UserRepository
import kotlinx.coroutines.launch

class PaymentViewModel(private val api: ApiService) : ViewModel() {

    private val _snapToken = MutableLiveData<String>()
    val snapToken: LiveData<String> = _snapToken

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchSnapToken(idTransaksi: String) {
        viewModelScope.launch {
            try {
                val response = api.createSnapToken(idTransaksi)
                if (response.isSuccessful) {
                    response.body()?.token?.let {
                        _snapToken.postValue(it)
                    } ?: _error.postValue("Snap Token kosong")
                } else {
                    _error.postValue("Gagal: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.localizedMessage}")
            }
        }
    }
}
