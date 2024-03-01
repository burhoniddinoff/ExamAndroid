package com.example.examandroid.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examandroid.createSpannableText
import com.example.examandroid.data.model.WeatherData
import com.example.examandroid.databinding.ItemWeatherBinding

class WeatherAdapter : ListAdapter<WeatherData, WeatherAdapter.InfoHolder>(InfoViewHolder) {

    private var onCLick: ((WeatherData) -> Unit)? = null
    private var query: String? = null

    fun setQuery(query: String?) {
        this.query = query
    }

    object InfoViewHolder : DiffUtil.ItemCallback<WeatherData>() {
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean = false
        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean = false
    }

    inner class InfoHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onCLick?.invoke(getItem(adapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            val data = getItem(adapterPosition)

            if (query != null) binding.countryName.text = data.name.createSpannableText(query!!)
            else binding.countryName.text = data.name


//            binding.countryName.text = data.name
            binding.cloudy.text = data.weatherType
            binding.gradusName.text = data.temprature
            binding.tText.text = "H: ${data.humidity}"
            binding.wTExt.text = "W: ${data.wind}"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder = InfoHolder(
        ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: InfoHolder, position: Int) = holder.bind()

    fun setOnCLick(block: (WeatherData) -> Unit) {
        onCLick = block
    }

}