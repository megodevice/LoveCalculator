package com.example.lovecalculator.utils

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreferences (context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        APP_SETTINGS, Context.MODE_PRIVATE
    )

    fun saveOnboarding(showOnboarding: Boolean) {
        sharedPreferences.edit().putBoolean(SHOW_ONBOARDING, showOnboarding).apply()
    }

    fun getOnboarding(): Boolean = sharedPreferences.getBoolean(SHOW_ONBOARDING, true)

    private companion object {
        const val SHOW_ONBOARDING = "SHOW_ONBOARDING"
        const val APP_SETTINGS = "APP_SETTINGS"
    }
}