package com.ama.algorithmmanagement.presentation.notip

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.activity.kDefault.NewSolvedProblemActivity
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityNoTipBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.domain.entity.TaggedProblem
import com.ama.algorithmmanagement.presentation.notip.adapter.NoTipProblemsAdapter
import timber.log.Timber
import java.util.*

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
        val intent = Intent(this, NewSolvedProblemActivity::class.java)
        intent.putIntegerArrayListExtra("problems", arrayListOf(clickProblem.problemId) as ArrayList<Int>?)
        startActivity(intent)

    }
}