package com.example.sample_retrofit.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.sample_retrofit.R

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val TOKEN = "token"
        const val USERNAME = "username"
        const val IS_LOGGED_IN = "is_logged_in"
    }

    fun saveAuthToken(token: String, username: String) {
        val editor = prefs.edit()
        editor.putString(TOKEN, token)
        editor.putString(USERNAME, username)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(TOKEN, null)
    }

    fun fetchAuthUsername(): String? {
        return prefs.getString(USERNAME, null)
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(IS_LOGGED_IN, false)

    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean(IS_LOGGED_IN, loggedIn).apply()
    }

    fun clearPref() {
        prefs.edit().clear().apply()
    }
}