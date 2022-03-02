package com.ama.algorithmmanagement.activity.test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.test.TestSearchAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseViewModelFactory
import com.ama.algorithmmanagement.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultSearchTestBinding
import com.ama.algorithmmanagement.viewmodel.test.SearchTestViewModel

class TestSearchActivity : KBaseActivity<DefaultSearchTestBinding>(R.layout.default_search_test) {

    private lateinit var viewModel: SearchTestViewModel
    private lateinit var searchAdapter: TestSearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[SearchTestViewModel::class.java]

        binding.viewModel = viewModel
        searchAdapter = TestSearchAdapter()
        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.setHasFixedSize(true)


        binding.inputKeyword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                sequence?.let { text ->

                    if (text.isNotBlank() && text.isNotEmpty()) {
                        viewModel.loadSearchData()
                    } else {
                        searchAdapter.removeAllItems()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

    }
}