package com.example.hackathonteam4.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHandler(private val context: Context) {
    private val settings: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return settings.getBoolean(key, false)
    }

    fun getString(key: String): String {
        return settings.getString(key, "")!!
    }

}