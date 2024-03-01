package com.example.examandroid.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.examandroid.R
import com.example.examandroid.databinding.ScreenInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoScreen : Fragment(R.layout.screen_info) {

    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val navArgs: InfoScreenArgs by navArgs()
    private val data by lazy { navArgs.data }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()

        binding.itemBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun initView() = binding.apply {

        countryName.text = data.name
        textHumidity.text = data.humidity
        textWind.text = data.wind
        textTemperature.text = data.temprature
        weatherText.text = data.weatherType

        when (data.weatherType) {
            "Qisman bulutli" -> imageWeather.setImageResource(R.drawable.ic_weather_1)
            "Kuchsiz qor" -> imageWeather.setImageResource(R.drawable.ic_weather_2)
            "Bulutli" -> imageWeather.setImageResource(R.drawable.ic_weather_3)
            "Qisman yomg’ir yog’ish ehtimoli" -> imageWeather.setImageResource(R.drawable.ic_weather_4)
            "Qisman yomgir yog'ish xavfi" -> imageWeather.setImageResource(R.drawable.ic_weather_4)
            "Kuchli qor" -> imageWeather.setImageResource(R.drawable.ic_weather_5)
            "O’rtacha qor" -> imageWeather.setImageResource(R.drawable.ic_weather_6)
            "Tuman" -> imageWeather.setImageResource(R.drawable.ic_weather_7)
        }


    }

}