package com.project.architecture.data.repository

import com.project.architecture.data.model.ProductsModel
import com.project.architecture.data.remote.ApiService
import com.project.architecture.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun sampleApiCall() : Flow<NetworkResult<ProductsModel>> = flow {
        try {
            val response = apiService.sampleApiCall()
            emit(NetworkResult.OnLoading())
            emit(NetworkResult.OnSuccess(response))
        }catch (e : Exception){
            emit(NetworkResult.OnError("${e.message} \n${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)
}