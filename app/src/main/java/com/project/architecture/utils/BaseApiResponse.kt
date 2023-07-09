package com.project.architecture.utils

class BaseApiResponse <T : Any> (private val response : NetworkResult<T>){
    fun execute(onSuccess : (t : T) -> Unit, onError : (t : String) -> Unit, onLoading : () -> Unit){
        when (response){
            is NetworkResult.OnSuccess -> {
                if(response.data == null){
                    onError("No Data")
                    return
                }
                response.data.let(onSuccess)
            }
            is NetworkResult.OnError -> {
                response.msg?.let(onError)
            }
            is NetworkResult.OnLoading -> {
                onLoading.invoke()
            }
        }
    }
}