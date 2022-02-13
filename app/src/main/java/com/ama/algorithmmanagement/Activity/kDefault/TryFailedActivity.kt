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

/**
 * 시도했으나 실패한 문제
 * author hongdroid94
 */
class TryFailedActivity : KBaseActivity<ActivityTryFailedBinding>(R.layout.activity_try_failed) {

    private lateinit var tryFailedViewModel: TryFailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryFailedViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[TryFailedViewModel::class.java]

        tryFailedViewModel.setSolvedacToken(
            getString(
                R.string.solvedacToken,
                "s:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJoYW5kbGUiOiJza2poMDgxOCIsImlhdCI6MTY0MDMyMjMyN30.McETfhdB0ifUV5DCAxPxv1lEKOWmrE2f1Lz1k5AQMKc.oSZuHUp7e6llQUYp2KHZx4utH1XSS8Ile8R4rrfuO6U"
            )
        )

        binding.viewModel = tryFailedViewModel
        binding.rvTryFailed.adapter = TryFailedAdapter()

    }
}