package com.project.architecture.data.remote
import com.project.architecture.data.model.ProductsModel
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
    suspend fun sampleApiCall() : ProductsModel
}