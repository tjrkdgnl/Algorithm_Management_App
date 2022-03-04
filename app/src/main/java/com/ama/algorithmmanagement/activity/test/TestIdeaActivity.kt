package com.ama.algorithmmanagement.activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.test.TestIdeaAdpater
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultIdeaTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestIdeaViewModel

class TestIdeaActivity : KBaseActivity<DefaultIdeaTestBinding>(R.layout.default_idea_test) {

//    @ExperimentalCoroutinesApi
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