package com.android.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<V : BaseViewModel, B : ViewBinding?> : Fragment(),
    BaseViewGroup<V, B?> {

    override var binding: B? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (view == null) {
            val bindingClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<B>

            binding = bindingClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            ).invoke(this, inflater, container, false) as B
        }

        with(viewModel){

            isLoadingData.observe(viewLifecycleOwner, Observer {visibility->
                onProgressBarVisibility(visibility)
            })
        }

        return binding?.root
    }

    abstract fun onProgressBarVisibility(visibility: Boolean)


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}