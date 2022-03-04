package com.ama.algorithmmanagement.activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ama.algorithmmanagement.adapter.TryHistoryAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.Fragment.PagerCommentFragment
import com.ama.algorithmmanagement.Fragment.PagerIdeaFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityTryHistoryBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.TryHistoryViewModel
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

/**
 * 문제 히스토리 보기
 * author hongdroid94
 */
class TryHistoryActivity : KBaseActivity<ActivityTryHistoryBinding>(R.layout.activity_try_history) {

    private lateinit var tryHistoryViewModel: TryHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getIntentData = intent
        var problemId: Int? = null
        if ( getIntentData.extras != null ) {
            problemId = getIntentData.getIntExtra("problemId", 0)
        }

        tryHistoryViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TryHistoryViewModel::class.java]

        val tryHistoryAdapter = TryHistoryAdapter(this)
        tryHistoryAdapter.addFragment(PagerCommentFragment(problemId))
        tryHistoryAdapter.addFragment(PagerIdeaFragment(problemId))

        binding.viewModel = tryHistoryViewModel
        binding.vpTryHistory.apply {
            this.adapter = tryHistoryAdapter
            this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Timber.d("current page is $position")
                }
            })
        }

        TabLayoutMediator(binding.tryHistoryTabLayout, binding.vpTryHistory) { tab, position ->
            if (position == 0)
                tab.text = "내가 남긴 질문 리스트"
            else
                tab.text = "아이디어"
        }.attach()
    }
}