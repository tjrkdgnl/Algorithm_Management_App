package com.ama.algorithmmanagement.activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.NoTipProblemsAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseViewModelFactory
import com.ama.algorithmmanagement.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityNoTipBinding
import com.ama.algorithmmanagement.model.TaggedProblem
import com.ama.algorithmmanagement.viewmodel.kDefault.NoTipViewModel
import timber.log.Timber

/**
 * 팁을 작성하지 않은 문제
 * author hongdroid94
 */
class NoTipActivity : KBaseActivity<ActivityNoTipBinding>(R.layout.activity_no_tip) {

    private lateinit var noTipViewModel: NoTipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noTipViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[NoTipViewModel::class.java]

        binding.viewModel = noTipViewModel
        binding.rvNoTipProblem.adapter = NoTipProblemsAdapter(listClickListener)
    }

    private val listClickListener : (TaggedProblem) -> Unit = { clickProblem ->
        Timber.d("problemId : ${clickProblem.problemId}")

        // 팁 작성 화면으로 이동
        // todo - 승호님 액티비티 작업 완료 시 활성화
//        val intent = Intent(this, ::class.java)
//        startActivity(intent)

    }
}