package com.ama.algorithmmanagement.Activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultLoginActivityBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.KLoginViewModel

class TestLoginActivity : KBaseActivity<DefaultLoginActivityBinding>(R.layout.default_login_activity) {
    private lateinit var loginViewModel: KLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(KLoginViewModel::class.java)
        binding.viewModel = loginViewModel
    }
}