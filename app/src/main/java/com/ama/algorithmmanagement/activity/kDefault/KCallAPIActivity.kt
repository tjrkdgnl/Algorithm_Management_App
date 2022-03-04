package com.ama.algorithmmanagement.activity.kDefault

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultActivityCallApiBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.KAPICallViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class KCallAPIActivity :
    KBaseActivity<DefaultActivityCallApiBinding>(R.layout.default_activity_call_api) {

    private lateinit var callViewModel: KAPICallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getFakeRepository(AMAApplication.INSTANCE))
        )[KAPICallViewModel::class.java]

        binding.viewModel = callViewModel
        binding.recyclerviewCallApi.adapter = KDefaultRecyclerViewAdapter()

    }
}