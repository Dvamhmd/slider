package com.example.dots

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object TokenManager {
    private const val PREFS_NAME = "auth"
    private const val KEY_AUTH_TOKEN = "auth_token"

    private var prefs: SharedPreferences? = null

    fun initialize(context: Context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveToken(token: String) {
        prefs?.edit { putString(KEY_AUTH_TOKEN, token) }
    }

    fun getToken(): String? {
        return prefs?.getString(KEY_AUTH_TOKEN, null)
    }

    fun clearToken() {
        prefs?.edit { remove(KEY_AUTH_TOKEN) }
    }
}
