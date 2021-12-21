package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultActivityCallApiBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.KAPICallViewModel

class KCallAPIActivity :
    KBaseActivity<DefaultActivityCallApiBinding>(R.layout.default_activity_call_api) {

    private lateinit var callViewModel: KAPICallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callViewModel = ViewModelProvider(this)[KAPICallViewModel::class.java]
        binding.viewModel = callViewModel
        binding.recyclerviewCallApi.adapter = KDefaultRecyclerViewAdapter()


    }
}