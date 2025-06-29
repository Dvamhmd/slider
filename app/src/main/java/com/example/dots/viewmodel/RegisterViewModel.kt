package com.example.dots.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dots.exception.RegistrationException
import com.example.dots.models.RegisterRequest
import com.example.dots.models.RegisterResponse
import com.example.dots.repository.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> = _registerResult

    fun register(data: RegisterRequest) {
        viewModelScope.launch {
            try {
                val result = repository.register(data)
                Log.d("RegisterViewModel 1", "result: $result")
                _registerResult.value = result
            } catch (e: HttpException) {
                val errorBodyStr = e.response()?.errorBody()?.string()
                Log.d("RegisterViewModel 2", "errorBody: $errorBodyStr") // Log isi JSON

                val errors = mutableMapOf<String, String>()

                if (!errorBodyStr.isNullOrEmpty()) {
                    try {
                        val json = JSONObject(errorBodyStr)
                        Log.d("RegisterViewModel 3", "full json: $json")
                        if (json.has("errors")) {
                            val fieldErrors = json.getJSONObject("errors")
                            Log.d("RegisterViewModel 4", "fieldErrors: $fieldErrors")
                            val keys = fieldErrors.keys()

                            while (keys.hasNext()) {
                                val key = keys.next()
                                val messages = fieldErrors.getJSONArray(key)
                                errors[key] = messages.getString(0) // Ambil pesan pertama
                            }

                            _registerResult.value = Result.failure(RegistrationException(errors))
                            return@launch
                        }
                    } catch (ex: Exception) {
                        Log.e("RegisterViewModel 5", "Parsing error: ${ex.message}")
                    }
                }
                Log.e("RegisterViewModel 6", "log: $e")
                _registerResult.value = Result.failure(e)
            }

        }
    }
}
