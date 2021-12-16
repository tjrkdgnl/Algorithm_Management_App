package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.Model.Problems
import com.ama.algorithmmanagement.Network.KAPIGenerator
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultActivityCallApiBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.KAPICallViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

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