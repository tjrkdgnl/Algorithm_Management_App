package com.ama.algorithmmanagement.Activity.kDefault

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.test.TestTipAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultActivitySolvedProblemsBinding
import com.ama.algorithmmanagement.viewmodel.test.TestTipViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class KCallSolvedAlgorithmAct :
    KBaseActivity<DefaultActivitySolvedProblemsBinding>(R.layout.default_activity_solved_problems) {

    private val moveToTippingAct: (TipProblemInfo) -> Unit = {
        val intent = Intent(applicationContext, KTestTippingItemAct::class.java)
        val bundle = Bundle()
        bundle.putParcelable("tipProblemInfo", it)
        intent.putExtra("problemBundle", bundle)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TestTipViewModel::class.java]

        binding.viewModel = callViewModel
        binding.recyclerviewCallApi.adapter = TestTipAdapter(moveToTippingAct)

    }
}