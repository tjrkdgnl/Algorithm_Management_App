package com.ama.algorithmmanagement.Activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultTippingItemBinding
import com.ama.algorithmmanagement.viewmodel.test.TestTipItemViewModel

class TestTippingItemAct :
    KBaseActivity<DefaultTippingItemBinding>(R.layout.default_tipping_item) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE),this)
        )[TestTipItemViewModel::class.java]

        viewmodel.setTipProblemInfo(
            intent.getBundleExtra("problemBundle")?.getParcelable("tipProblemInfo")
        )

        binding.viewModel = viewmodel
    }
}