package com.example.random_user.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import kotlin.reflect.typeOf

abstract class BaseFragment<VM: ViewModel, VB : ViewBinding>
    : Fragment() {
    abstract val viewModel: VM

    abstract val viewBindingProvider: (LayoutInflater, ViewGroup?) -> VB
    private var bindingInternal: VB? = null
    protected val binding: VB
        get() = bindingInternal!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingInternal = viewBindingProvider(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInternal = null
    }

    @ExperimentalStdlibApi
    private inline fun <reified VM> getTypeViewModel() = typeOf<VM>()
}