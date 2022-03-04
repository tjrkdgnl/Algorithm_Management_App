package com.ama.algorithmmanagement.activity.kDefault

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.activity.test.TestTippingItemAct
import com.ama.algorithmmanagement.adapter.test.TestTipAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.data.model.TipProblemInfo
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultActivitySolvedProblemsBinding
import com.ama.algorithmmanagement.viewmodel.test.TestTipViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class KCallSolvedAlgorithmAct :
    KBaseActivity<DefaultActivitySolvedProblemsBinding>(R.layout.default_activity_solved_problems) {

    private val moveToTippingAct: (TipProblemInfo) -> Unit = {
        val intent = Intent(applicationContext, TestTippingItemAct::class.java)
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