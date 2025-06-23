package com.example.dots.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dots.models.LoginResponse
import com.example.dots.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application.applicationContext)

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    fun login(login: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(login, password)
            _loginResult.postValue(result)
        }
    }

    fun logout() {
        repository.logout()
    }
}
