package com.ama.algorithmmanagement.activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.test.TestDateAdpater
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseViewModelFactory
import com.ama.algorithmmanagement.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.repositories.RepositoryLocator
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
        binding.dateRecyclerView.setHasFixedSize(false)


    }
}