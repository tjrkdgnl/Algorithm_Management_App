package com.ama.algorithmmanagement.presentation.status;

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityCategorySolvedProblemBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity

/**
 * @author SeungHo Lee
 * summary : 메인화면에서 유형별통계 더보기 버튼 누를시  모든 유형에대한 통계정보
 */
class CategoryStatusActivity :
    KBaseActivity<ActivityCategorySolvedProblemBinding>(R.layout.activity_category_solved_problem) {
    private lateinit var viewModel: CategoryStatusViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[CategoryStatusViewModel::class.java]
        binding.viewmodel = viewModel
    }

}