package com.ama.algorithmmanagement.activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.KChildCommentsAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseViewModelFactory
import com.ama.algorithmmanagement.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMyChildCommentBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.MyChildCommentViewModel

/**
 * author  : hongdroid94
 * summary : 대 댓글 화면
 */
class MyChildCommentActivity : KBaseActivity<ActivityMyChildCommentBinding>(R.layout.activity_my_child_comment) {

    private lateinit var myChildCommentViewModel: MyChildCommentViewModel
    private lateinit var commentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent != null && intent.hasExtra("commentId")) {
            commentId = intent.getStringExtra("commentId").toString()
        }

        myChildCommentViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[MyChildCommentViewModel::class.java]
        binding.viewModel = myChildCommentViewModel
        myChildCommentViewModel.setCommentId(commentId)

        binding.rvMyChildComment.setHasFixedSize(true)
        binding.rvMyChildComment.adapter = KChildCommentsAdapter()
    }
}