package com.example.examandroid.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.examandroid.R
import com.example.examandroid.databinding.ScreenWeatherBinding
import com.example.examandroid.presentation.adapter.WeatherAdapter
import com.example.examandroid.presentation.viewModel.WeatherVM
import com.example.examandroid.presentation.viewModel.impl.WeatherVMImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class WeatherScreen : Fragment(R.layout.screen_weather) {

    private val binding by viewBinding(ScreenWeatherBinding::bind)
    private val viewModel: WeatherVM by viewModels<WeatherVMImpl>()
    private var adapter = WeatherAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initAdapter()
        initVM()

        binding.searchLinear.setOnClickListener {
            findNavController().navigate(WeatherScreenDirections.actionWeatherScreenToSearchScreen())
        }

    }

    private fun initVM() {
        viewModel.loadWeather()

        viewModel.error.onEach {
            Toast.makeText(requireContext(), "$it error", Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

        viewModel.success.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

    }

    private fun initAdapter() {
        adapter = WeatherAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnCLick {
            findNavController().navigate(WeatherScreenDirections.actionWeatherScreenToInfoScreen(it))
//            viewModel.infoScreen(it)
        }

    }


}