package com.ama.algorithmmanagement.Activity

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.KViewPagerAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.Fragment.ChildCommentViewFragment
import com.ama.algorithmmanagement.Fragment.CommentViewFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityViewProblemDetailBinding
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.viewmodel.KViewProblemDetailViewModel
import timber.log.Timber

/**
 * author : manyong Han
 * summary : 문제 상세 보기 액티비티
 */
class KViewProblemDetailActivity : KBaseActivity<ActivityViewProblemDetailBinding>(R.layout.activity_view_problem_detail), AdapterListener {

    private lateinit var viewProblemDetailViewModel: KViewProblemDetailViewModel
    private lateinit var viewPagerAdapter: KViewPagerAdapter
    private lateinit var fragments: List<Fragment>
    private var problemId = "1010"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewProblemDetailViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[KViewProblemDetailViewModel::class.java]

        binding.viewModel = viewProblemDetailViewModel
        setFragment()

//        if(intent != null && intent.hasExtra("problemId")) {
//            problemId = intent.getStringExtra("problemId").toString()
//        }

        viewPagerAdapter = KViewPagerAdapter(supportFragmentManager, fragments ,lifecycle)
        binding.viewProblemCommentViewPager.adapter = viewPagerAdapter
        binding.viewProblemCommentViewPager.isUserInputEnabled = false

        binding.connectBojWebView.apply {
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
        }

        viewProblemDetailViewModel.isGetProblemId.observe(this) {
            if(it) {
                viewProblemDetailViewModel.problemId.value = problemId.toInt()
                viewProblemDetailViewModel.isGetProblemId.value = false
                viewProblemDetailViewModel.getProblemInfo()
                viewProblemDetailViewModel.getCommentListLoading()
            }
        }

        viewProblemDetailViewModel.isMoveToBOJWebView.observe(this) {
            if(it) {
                binding.connectBojWebView .loadUrl("https://www.acmicpc.net/problem/$problemId")
                binding.connectLayout.visibility = View.VISIBLE
                binding.viewProblemPage.visibility = View.GONE
            } else {
                binding.connectBojWebView.loadUrl("")
                binding.connectLayout.visibility = View.GONE
                binding.viewProblemPage.visibility = View.VISIBLE
            }
            binding.viewProblemScroll.fullScroll(NestedScrollView.FOCUS_UP)
        }

        viewProblemDetailViewModel.isBackCommentFrag.observe(this) {
            if(it) {
                moveFragment(0)
                viewProblemDetailViewModel.isBackCommentFrag.value = false
            }
        }

        viewProblemDetailViewModel.isCommentIdGet.observe(this) {
            if(it) {
                // 이거 메서드 어떻게 실행시킬지 고민..
                viewProblemDetailViewModel.getChildCommentListLoading()
                moveFragment(1)
                viewProblemDetailViewModel.isCommentIdGet.value = false
            }
        }
    }

    private fun setFragment(){
        fragments = arrayListOf(
            CommentViewFragment(this),
            ChildCommentViewFragment()
        )
    }

    private fun moveFragment(position: Int) {
        Timber.e("호출되었으~~ : $position")
        binding.viewProblemCommentViewPager.currentItem = position
    }

    override fun adapterClickListener(commentInfo: CommentInfo) {
        Timber.e("commentId : ${commentInfo.commentId}")

        viewProblemDetailViewModel.selectCommentId.value = commentInfo.commentId
        viewProblemDetailViewModel.selectComment.value = commentInfo.comment
        viewProblemDetailViewModel.selectCommentUserId.value = commentInfo.userId
        viewProblemDetailViewModel.selectCommentDate.value = commentInfo.date

        viewProblemDetailViewModel.isCommentIdGet.value = true
    }
}

interface AdapterListener {
    fun adapterClickListener(commentInfo: CommentInfo)
}