package com.project.architecture

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(), LifecycleObserver {

    private val lifeCycleEventObserver = LifecycleEventObserver{ source, event ->
        when(event){
            Lifecycle.Event.ON_START -> {

            }
            Lifecycle.Event.ON_CREATE -> {

            }
            Lifecycle.Event.ON_DESTROY-> {

            }
            else -> {}
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.v("CurrentActivity", this::class.java.name)
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifeCycleEventObserver)
    }
}