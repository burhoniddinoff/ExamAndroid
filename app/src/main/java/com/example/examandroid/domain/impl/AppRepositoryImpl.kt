package com.example.examandroid.domain.impl

import com.example.examandroid.data.model.WeatherData
import com.example.examandroid.domain.AppRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
) : AppRepository {

    override fun getWeather(): Flow<Result<List<WeatherData>>> = callbackFlow {
        fireStore.collection("weather")
            .get()
            .addOnSuccessListener { qs ->
                val data = ArrayList<WeatherData>()
                qs.forEach {
                    val name = it.id
                    val humidity = it.data.getOrDefault("humidity", "$#").toString()
                    val temprature = it.data.getOrDefault("temprature", "$#").toString()
                    val weatherType = it.data.getOrDefault("weatherType", "$#").toString()
                    val wind = it.data.getOrDefault("wind", "$#").toString()

                    data.add(WeatherData(name, humidity, temprature, weatherType, wind))
                }

                trySend(Result.success(data))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }


    override fun getSearchWeather(name: String): Flow<Result<List<WeatherData>>> = callbackFlow {
        if (name.trim().isEmpty())
            trySend(Result.success(arrayListOf()))
        else {
            fireStore.collection("weather")
                .get()
                .addOnSuccessListener { qs ->
                    val data = ArrayList<WeatherData>()
                    qs.forEach {
                        val cName = it.id
                        if (cName.lowercase().contains(name.lowercase())) {

                            val humidity = it.data.getOrDefault("humidity", "$#").toString()
                            val temprature = it.data.getOrDefault("temprature", "$#").toString()
                            val weatherType = it.data.getOrDefault("weatherType", "$#").toString()
                            val wind = it.data.getOrDefault("wind", "$#").toString()

                            data.add(WeatherData(cName, humidity, temprature, weatherType, wind))
                        }
                    }

                    trySend(Result.success(data))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose()
        }
    }

}
