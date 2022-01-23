package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ama.algorithmmanagement.Adapter.TryHistoryAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.Fragment.PagerCommentFragment
import com.ama.algorithmmanagement.Fragment.PagerIdeaFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityTryHistoryBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.TryHistoryViewModel
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class TryHistoryActivity : KBaseActivity<ActivityTryHistoryBinding>(R.layout.activity_try_history) {

    private lateinit var tryHistoryViewModel: TryHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryHistoryViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getFakeRepository(AMAApplication.INSTANCE))
        )[TryHistoryViewModel::class.java]

        val tryHistoryAdapter = TryHistoryAdapter(this)
        tryHistoryAdapter.addFragment(PagerCommentFragment())
        tryHistoryAdapter.addFragment(PagerIdeaFragment())

        binding.viewModel = tryHistoryViewModel
        binding.vpTryHistory.let {
            it.adapter = tryHistoryAdapter
            it.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Timber.d("current page is $position")
                }
            })
        }

        TabLayoutMediator(binding.tryHistoryTabLayout, binding.vpTryHistory) { tab, position ->
            if (position == 0)
                tab.text = "나의댓글"
            else
                tab.text = "아이디어"
        }.attach()

    }
}