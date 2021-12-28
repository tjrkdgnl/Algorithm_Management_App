package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultFirebaseTestBinding
import com.ama.algorithmmanagement.viewmodel.test.RealViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class KDefaultFirebaseTest :
    KBaseActivity<DefaultFirebaseTestBinding>(R.layout.default_firebase_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[RealViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.setSolvedacToken(
            getString(
                R.string.solvedacToken,
                "s:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJoYW5kbGUiOiJza2poMDgxOCIsImlhdCI6MTY0MDMyMjMyN30.McETfhdB0ifUV5DCAxPxv1lEKOWmrE2f1Lz1k5AQMKc.oSZuHUp7e6llQUYp2KHZx4utH1XSS8Ile8R4rrfuO6U"
            )
        )


    }
}