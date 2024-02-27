package com.example.lovecalculator.di

import android.content.Context
import com.example.lovecalculator.remote.LoveAPI
import com.example.lovecalculator.utils.AppSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideLoveApi(): LoveAPI =
        Retrofit.Builder().baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(LoveAPI::class.java)

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): AppSharedPreferences =
        AppSharedPreferences(context = context)
}
