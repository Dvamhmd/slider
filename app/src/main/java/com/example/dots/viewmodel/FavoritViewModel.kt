package com.example.dots.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.dots.models.Favorit
import com.example.dots.repository.FavoritRepository
import kotlinx.coroutines.launch

class FavoritViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoritRepository(application.applicationContext)

    private val _favoritList = MutableLiveData<List<Favorit>?>()
    val favoritList: LiveData<List<Favorit>> = _favoritList as LiveData<List<Favorit>>

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchFavorit() {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.getFavorit()
            _loading.value = false

            result.onSuccess { response ->
                _favoritList.value = response.data
                _error.value = null
            }.onFailure { e ->
                _error.value = e.message ?: "Terjadi kesalahan saat mengambil data favorit"
            }
        }
    }

    fun tambahFavorit(idProduk: String, idToko: String) {
        viewModelScope.launch {
            try {
                repository.tambahFavorit(idProduk, idToko)
                fetchFavorit()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
