package com.project.architecture.ui

import android.util.Log
import androidx.activity.viewModels
import com.project.architecture.BaseActivity
import com.project.architecture.databinding.ActivityMainBinding
import com.project.architecture.ui.viewmodel.MainViewModel
import com.project.architecture.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate,
) {
    private val mainViewModel : MainViewModel by viewModels()

    override fun ActivityMainBinding.initCall() {
        mainViewModel.callSampleApi()
    }

    override fun ActivityMainBinding.initObserver() {
        observe(mainViewModel.apiResponse){
            binding.textViewX.text = it.products.toString()
        }
        observe(mainViewModel.errorMessage){
            Log.d("ApiXXX", "$it ")
        }
    }

    override fun ActivityMainBinding.initialize() {

    }


}