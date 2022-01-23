package com.ama.algorithmmanagement.Activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.test.TestIdeaAdpater
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultIdeaTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestIdeaViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TestIdeaActivity : KBaseActivity<DefaultIdeaTestBinding>(R.layout.default_idea_test) {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE),this)
        )[TestIdeaViewModel::class.java]

        binding.viewModel = viewModel
        binding.ideaRecyclerView.adapter = TestIdeaAdpater()
        binding.ideaRecyclerView.setHasFixedSize(false)

        viewModel.ideaInfos.observe(this,{ ideas ->
            ideas?.let {
                viewModel.ideaList.addAll(it.ideaList)

            }
        })

    }
}