package com.example.sample_retrofit.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.sample_retrofit.R

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val TOKEN = "token"
        const val USERNAME = "username"
    }

    /**
     * Function to save auth token and username
     */
    fun saveAuthToken(token: String, username: String) {
        val editor = prefs.edit()
        editor.putString(TOKEN, token)
        editor.putString(USERNAME, username)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(TOKEN, null)
    }

    /**
     * Function to fetch auth username
     */
    fun fetchAuthUsername(): String? {
        return prefs.getString(USERNAME, null)
    }
}