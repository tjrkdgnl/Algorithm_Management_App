package com.ama.algorithmmanagement.Activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.test.TestDateAdpater
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultDateTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestDateViewModel

class TestDateActivity : KBaseActivity<DefaultDateTestBinding>(R.layout.default_date_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TestDateViewModel::class.java]

        binding.viewModel = viewModel

        binding.dateRecyclerView.adapter = TestDateAdpater()
//        binding.dateRecyclerView.setHasFixedSize(false)
 

    }
}