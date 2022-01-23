package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.TryFailedAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityTryFailedBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.TryFailedViewModel

class TryFailedActivity : KBaseActivity<ActivityTryFailedBinding>(R.layout.activity_try_failed) {

    private lateinit var tryFailedViewModel: TryFailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryFailedViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getFakeRepository(AMAApplication.INSTANCE))
        )[TryFailedViewModel::class.java]

        binding.viewModel = tryFailedViewModel
        binding.rvTryFailed.adapter = TryFailedAdapter()
    }
}