package com.ama.algorithmmanagement.presentation.vpdetail.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.common.adapter.KViewPagerAdapter
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityViewProblemDetailBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.domain.entity.CommentInfo
import com.ama.algorithmmanagement.presentation.vpdetail.fragment.ChildCommentViewFragment
import com.ama.algorithmmanagement.presentation.vpdetail.fragment.CommentViewFragment
import com.ama.algorithmmanagement.presentation.vpdetail.view_model.KViewProblemDetailViewModel
import timber.log.Timber

/**
 * author : manyong Han
 * summary : 문제 상세 보기 액티비티
 */

class KViewProblemDetailActivity : KBaseActivity<ActivityViewProblemDetailBinding>(R.layout.activity_view_problem_detail) {

    private lateinit var viewProblemDetailViewModel: KViewProblemDetailViewModel
    private lateinit var viewPagerAdapter: KViewPagerAdapter
    private lateinit var fragments: List<Fragment>
    private lateinit var problemId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("problemId")) {
            problemId = intent.getStringExtra("problemId").toString()
        }

        viewProblemDetailViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[KViewProblemDetailViewModel::class.java]

        binding.viewModel = viewProblemDetailViewModel
        setFragment()

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
                viewProblemDetailViewModel.getCommentListLoading()
                moveFragment(0)
                viewProblemDetailViewModel.isBackCommentFrag.value = false
            }
        }

        viewProblemDetailViewModel.isCommentIdGet.observe(this) {
            if(it) {
                viewProblemDetailViewModel.getChildCommentListLoading()
                moveFragment(1)
                viewProblemDetailViewModel.isCommentIdGet.value = false
            }
        }
    }

    private fun setFragment(){
        fragments = arrayListOf(
            CommentViewFragment(childClickListener),
            ChildCommentViewFragment()
        )
    }

    private fun moveFragment(position: Int) {
        Timber.e("호출되었으~~ : $position")
        binding.viewProblemCommentViewPager.currentItem = position
    }

    // 댓글 클릭 콜백
    private val childClickListener :(CommentInfo) -> Unit = { commentInfo ->
        Timber.e("commentId : ${commentInfo.commentId}")

        viewProblemDetailViewModel.selectCommentId.value = commentInfo.commentId
        viewProblemDetailViewModel.selectComment.value = commentInfo.comment
        viewProblemDetailViewModel.selectCommentUserId.value = commentInfo.userId
        viewProblemDetailViewModel.selectCommentDate.value = commentInfo.date

        viewProblemDetailViewModel.isCommentIdGet.value = true
    }
}