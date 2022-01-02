package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.test.IdeaAdpater
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultIdeaTestBinding
import com.ama.algorithmmanagement.viewmodel.test.IdeaViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class IdeaActivity : KBaseActivity<DefaultIdeaTestBinding>(R.layout.default_idea_test) {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE),this)
        )[IdeaViewModel::class.java]

        binding.viewModel = viewModel
        binding.ideaRecyclerView.adapter = IdeaAdpater()
        binding.ideaRecyclerView.setHasFixedSize(false)

        viewModel.ideaInfos.observe(this,{ ideas ->
            ideas?.let {
                viewModel.ideaList.addAll(it.ideaList)

            }
        })

    }
}