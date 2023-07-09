package com.project.architecture.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.architecture.data.model.ProductsModel
import com.project.architecture.data.repository.MainRepository
import com.project.architecture.utils.BaseApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    private val _apiResponse = MutableLiveData<ProductsModel>()
    val apiResponse : LiveData<ProductsModel> get() = _apiResponse

    fun callSampleApi() = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.sampleApiCall().collect{
            BaseApiResponse(it).execute(
                onSuccess = { response -> _apiResponse.postValue(response)},
                onError = { msg -> errorMessage.postValue(msg)},
                onLoading = {
                }
            )
        }
    }
}