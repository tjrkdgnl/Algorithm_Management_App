package com.ama.algorithmmanagement.domain.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class KBaseActivity<T: ViewDataBinding>(@LayoutRes private val layoutRes: Int) :AppCompatActivity() {
    protected lateinit var  binding : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutRes)
        binding.lifecycleOwner= this

        this.applicationContext
    }
}