package com.example.dots.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dots.models.Alamat
import com.example.dots.repository.AlamatRepository
import kotlinx.coroutines.launch

class AlamatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlamatRepository(application.applicationContext)

    private val _alamatList = MutableLiveData<List<Alamat>?>()
    val alamatList: LiveData<List<Alamat>?> get() = _alamatList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _tambahAlamatResult = MutableLiveData<Alamat?>()
    val tambahAlamatResult: LiveData<Alamat?> get() = _tambahAlamatResult

    private val _editAlamatResult = MutableLiveData<Alamat?>()
    val editAlamatResult: LiveData<Alamat?> get() = _editAlamatResult

    fun fetchAlamat() {
        _loading.value = true
        viewModelScope.launch {
            val result = repository.getAlamat()
            _loading.value = false
            result.onSuccess {
                _alamatList.value = it
                _error.value = null
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    fun tambahAlamat(alamat: Alamat) {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.tambahAlamat(alamat)
            _loading.value = false

            result.onSuccess {
                _tambahAlamatResult.value = it
                _error.value = null
            }.onFailure {
                _tambahAlamatResult.value = null
                _error.value = it.message
            }
        }
    }

    fun clearTambahResult() {
        _tambahAlamatResult.value = null
    }

    fun editAlamat(alamat: Alamat) {
          _loading.value = true
          viewModelScope.launch {
                val result = repository.editAlamat(alamat)
                _loading.value = false
                result.onSuccess {
                    fetchAlamat()
                  _editAlamatResult.value = it
                  _error.value = null
                }.onFailure {
                  _editAlamatResult.value = null
                  _error.value = it.message
                }
              }
    }

    fun deleteAlamat(id: String) {
          _loading.value = true
          viewModelScope.launch {
                val result = repository.deleteAlamat(id)
                _loading.value = false
                result.onSuccess {
                  fetchAlamat()
                  _error.value = null
                }.onFailure {
                  _error.value = it.message
                }
              }
    }

    fun setAlamatUtama(selectedAlamat: Alamat) {
        val currentList = _alamatList.value ?: return

        // Clear semua status "utama"
        val updatedList = currentList.map {
            if (it.idAlamat == selectedAlamat.idAlamat) {
                it.copy(status = "utama")
            } else {
                it.copy(status = "")
            }
        }

        // Update ke LiveData agar adapter refresh
        _alamatList.postValue(updatedList)

        // Kirim perubahan ke server: hanya alamat yang jadi utama
        editAlamat(selectedAlamat.copy(status = "utama"))
    }



}
