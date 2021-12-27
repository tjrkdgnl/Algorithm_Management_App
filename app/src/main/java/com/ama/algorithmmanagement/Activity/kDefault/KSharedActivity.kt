package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSharedprefTestBinding
import com.ama.algorithmmanagement.viewmodel.test.SharedViewModel

class KSharedActivity :
    KBaseActivity<DefaultSharedprefTestBinding>(R.layout.default_sharedpref_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: SharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        binding.viewModel = viewModel


        viewModel._tokenCheck.observe(this, {
            if (it) {
                viewModel.setToken(
                    getString(
                        R.string.solvedacToken,
                        "s:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJoYW5kbGUiOiJza2poMDgxOCIsImlhdCI6MTY0MDMyMjMyN30.McETfhdB0ifUV5DCAxPxv1lEKOWmrE2f1Lz1k5AQMKc.oSZuHUp7e6llQUYp2KHZx4utH1XSS8Ile8R4rrfuO6U"
                    )
                )
                viewModel.initTokenState()
            }
        })

    }
}