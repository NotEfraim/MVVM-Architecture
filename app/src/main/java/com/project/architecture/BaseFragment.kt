package com.project.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewDataBinding>(
    private val bindingInflater : (inflater: LayoutInflater) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    val binding : VB get() = _binding.let { binding ->

        when(binding){
            null -> bindingInflater(layoutInflater)
            else -> binding
        }

    }

    open fun VB.initialize(){}
    open fun VB.initObserver(){}
    open fun VB.initCall(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        binding.initCall()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.initialize()
        binding.initObserver()
        return binding.root
    }


}