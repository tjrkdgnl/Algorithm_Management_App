package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.MyTipProblemsAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMyTipBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.MyTipViewModel

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
        binding.rvMyTipProblem.adapter = MyTipProblemsAdapter()
    }
}