package com.ama.algorithmmanagement.Activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.KSolvedProblemsAdapter
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultActivitySolvedProblemsBinding
import com.ama.algorithmmanagement.viewmodel.KAPICallViewModel

class KCallSolvedAlgorithmAct :
    KBaseActivity<DefaultActivitySolvedProblemsBinding>(R.layout.default_activity_solved_problems) {

    private lateinit var callViewModel: KAPICallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callViewModel = ViewModelProvider(this).get(KAPICallViewModel::class.java)
        binding.viewModel = callViewModel
        binding.recyclerviewCallApi.adapter = KSolvedProblemsAdapter()

    }
}