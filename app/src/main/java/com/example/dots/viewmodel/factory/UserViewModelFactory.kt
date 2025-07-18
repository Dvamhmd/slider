package com.example.dots.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dots.repository.UserRepository
import com.example.dots.viewmodel.UserViewModel

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val repository = UserRepository(context)
            return UserViewModel(repository) as T
          }
}
