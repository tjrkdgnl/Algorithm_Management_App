package com.ama.algorithmmanagement.activity.kDefault

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.MyTipProblemsAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMyTipBinding
import com.ama.algorithmmanagement.data.model.TaggedProblem
import com.ama.algorithmmanagement.viewmodel.kDefault.MyTipViewModel
import timber.log.Timber

/**
 * 내가 작성한 팁
 * author hongdroid94
 */
class MyTipActivity : KBaseActivity<ActivityMyTipBinding>(R.layout.activity_my_tip) {

    private lateinit var myTipViewModel: MyTipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myTipViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[MyTipViewModel::class.java]

        binding.viewModel = myTipViewModel
        binding.rvMyTipProblem.adapter = MyTipProblemsAdapter(listClickListener)
    }

    private val listClickListener : (TaggedProblem) -> Unit = { clickProblem ->
        Timber.d("problemId : ${clickProblem.problemId}")

        val intent = Intent(this, NewSolvedProblemActivity::class.java)
        intent.putExtra("problemId", clickProblem.problemId.toString())
        startActivity(intent)
    }
}