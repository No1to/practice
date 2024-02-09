package com.example.practice.data.local

import android.content.Context

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun setName(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String? {
        return pref.getString(NAME_KEY, "")
    }

    companion object {
        const val PREF_NAME = "pref.name"
        const val NAME_KEY = "name.key"
    }

}