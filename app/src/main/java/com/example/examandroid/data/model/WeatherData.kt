package com.example.examandroid.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherData(
    val name: String,
    val humidity: String,
    val temprature: String,
    val weatherType: String,
    val wind: String
) : Parcelable