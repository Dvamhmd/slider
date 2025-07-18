package com.example.dots.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dots.models.User
import com.example.dots.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

      private val _user = MutableLiveData<User?>()
      val user: LiveData<User?> get() = _user

      private val _error = MutableLiveData<String?>()
      val error: LiveData<String?> get() = _error

      private val _updateResult = MutableLiveData<Result<User>>()
      val updateResult: LiveData<Result<User>> get() = _updateResult

      fun fetchUser() {
            viewModelScope.launch {
                  val result = repository.getUser()
                  if (result.isSuccess) {
                    _user.postValue(result.getOrNull())
                  } else {
                    _error.postValue(result.exceptionOrNull()?.message ?: "Terjadi kesalahan")
                  }
                }
          }

    fun updateProfile(name: String, username: String, email: String, phone: String, photoData: ByteArray?) {
        viewModelScope.launch {
            val result = repository.updateProfile(name, username, email, phone, photoData)
            _updateResult.postValue(result)
        }
    }

}
