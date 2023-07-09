package com.project.architecture.utils

sealed class NetworkResult <T> (val data : T? = null, val msg : String? = null){
    class OnLoading<T> : NetworkResult<T>()
    class OnSuccess<T>(data: T) : NetworkResult<T>(data = data)
    class OnError<T>(msg : String) : NetworkResult<T>(msg = msg)
}