package com.ama.algorithmmanagement.activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultFirebaseTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestRealViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TestFirebaseTest :
    KBaseActivity<DefaultFirebaseTestBinding>(R.layout.default_firebase_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[TestRealViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.setSolvedacToken(
            getString(
                R.string.solvedacToken,
                "s:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJoYW5kbGUiOiJza2poMDgxOCIsImlhdCI6MTY0MDMyMjMyN30.McETfhdB0ifUV5DCAxPxv1lEKOWmrE2f1Lz1k5AQMKc.oSZuHUp7e6llQUYp2KHZx4utH1XSS8Ile8R4rrfuO6U"
            )
        )


    }
}