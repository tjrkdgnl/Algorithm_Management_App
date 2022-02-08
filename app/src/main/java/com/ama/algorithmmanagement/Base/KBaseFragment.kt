package com.ama.algorithmmanagement.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class KBaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : Fragment() {

    private var bindingObj: T? = null
    protected val binding: T by lazy {
        bindingObj!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingObj = DataBindingUtil.inflate(layoutInflater, layoutRes, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        bindingObj = null
    }
}