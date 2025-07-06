package com.example.dots.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.dots.models.BaseResponse
import com.example.dots.models.Kategori
import com.example.dots.repository.KategoriRepository
import kotlinx.coroutines.launch
import kotlin.onSuccess

class KategoriViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = KategoriRepository(application.applicationContext)

    private val _kategoriList = MutableLiveData<List<Kategori>?>()
    val kategoriList: LiveData<List<Kategori>> = _kategoriList as LiveData<List<Kategori>>

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchKategori() {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.getKategori()
            _loading.value = false

            result.onSuccess { response ->
                _kategoriList.value = response.data
                _error.value = null
            }.onFailure { e ->
                _error.value = e.message ?: "Terjadi kesalahan"
            }
        }
    }
}
