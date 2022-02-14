package com.ama.algorithmmanagement.Activity.kDefault;

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.TipProblemViewPagerAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityNewSolvedProblemBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-12
 * class : NewSolvedProblem.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class NewSolvedProblemActivity :KBaseActivity<ActivityNewSolvedProblemBinding>(R.layout.activity_new_solved_problem){
    private lateinit var viewModel:NewSolvedProblemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,BaseViewModelFactory(
            RepositoryLocator().getRepository(AMAApplication.INSTANCE)
        ))[NewSolvedProblemViewModel::class.java]
        binding.viewmodel = viewModel
        Timber.e("여긴 되나")
        val adapter = TipProblemViewPagerAdapter { viewModel.skipTipPage() }
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false // 뷰페어져 슬와이프 막기





    }

}