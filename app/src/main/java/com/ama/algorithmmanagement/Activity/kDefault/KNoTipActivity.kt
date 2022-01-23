package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.KNoTipProblemsAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityNoTipBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.KNoTipViewModel

class KNoTipActivity : KBaseActivity<ActivityNoTipBinding>(R.layout.activity_no_tip) {

    private lateinit var noTipViewModel: KNoTipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noTipViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getFakeRepository(AMAApplication.INSTANCE))
        )[KNoTipViewModel::class.java]

        binding.viewModel = noTipViewModel
        binding.rvNoTipProblem.adapter = KNoTipProblemsAdapter()
    }
}