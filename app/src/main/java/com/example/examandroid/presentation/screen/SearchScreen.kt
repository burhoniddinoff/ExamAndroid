package com.example.examandroid.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.examandroid.R
import com.example.examandroid.databinding.ScreenSearchBinding
import com.example.examandroid.myLog
import com.example.examandroid.presentation.adapter.WeatherAdapter
import com.example.examandroid.presentation.viewModel.SearchVM
import com.example.examandroid.presentation.viewModel.impl.SearchVMImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.screen_search) {

    private val binding by viewBinding(ScreenSearchBinding::bind)
    private val viewModel: SearchVM by viewModels<SearchVMImpl>()

    private val adapter = WeatherAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()
        initFlow()

    }

    private fun initView() = binding.apply {

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnCLick {
            findNavController().navigate(SearchScreenDirections.actionSearchScreenToInfoScreen(it))
        }

        searchBack.setOnClickListener {
//            viewModel.onClickBack()
            findNavController().navigateUp()
        }

        searchText.addTextChangedListener {
            if (it?.isEmpty() == false) {
                viewModel.textChange(searchText.text.toString())
                adapter.setQuery(searchText.text.toString())

                "${searchText.text}  searchText name".myLog()
            } else {
                adapter.submitList(arrayListOf())
                adapter.setQuery(null)
            }
        }

    }

    private fun initFlow() = binding.apply {

        viewModel.result.onEach {

            adapter.submitList(it)

            "$it data".myLog()
            "${it.size} data size".myLog()

        }.launchIn(lifecycleScope)

    }

}