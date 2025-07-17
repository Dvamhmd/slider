package com.example.dots.viewmodel

import android.util.Log
import com.example.dots.repository.KeranjangRepository
import androidx.lifecycle.*
import com.example.dots.models.Keranjang
import com.example.dots.utilities.formatRupiah
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch


class KeranjangViewModel(private val repository: KeranjangRepository) : ViewModel() {

    private val _keranjangList = MutableLiveData<List<Keranjang>>()
    val keranjangList: LiveData<List<Keranjang>> = _keranjangList

    private val _totalHarga = MutableLiveData<Int>()
    val totalHarga: LiveData<Int> = _totalHarga

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchKeranjang() {
        viewModelScope.launch {
            try {
                val response = repository.getKeranjang()
                val dataMap = response.data ?: return@launch

                val keranjangJson = Gson().toJson(dataMap["keranjang"])
                val type = object : TypeToken<List<Keranjang>>() {}.type
                val list: List<Keranjang> = Gson().fromJson(keranjangJson, type)

                val total = dataMap["total_harga"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0
                Log.d("KeranjangViewModel", "Parsed total harga: ${formatRupiah(total)}")



                _keranjangList.value = list
                _totalHarga.value = total
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }


    fun tambahKeranjang(idProduk: String, idToko: String, jumlah: Int = 1) {
        viewModelScope.launch {
            try {
                repository.tambahKeranjang(idProduk, idToko, jumlah)
                fetchKeranjang()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun hapusKeranjang(idKeranjang: Keranjang) {
        viewModelScope.launch {
            try {
                repository.hapusKeranjang(idKeranjang)
                fetchKeranjang() // Refresh list setelah dihapus
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

}
