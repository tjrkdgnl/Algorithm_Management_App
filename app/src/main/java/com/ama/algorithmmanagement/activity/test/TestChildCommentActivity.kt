package com.ama.algorithmmanagement.activity.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.test.TestChildCommentAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultChildcommentTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestChildCommentViewModel

class TestChildCommentActivity :
    KBaseActivity<DefaultChildcommentTestBinding>(R.layout.default_childcomment_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val commentId = intent.getBundleExtra("commentBundle")?.getString("commentId")
        val problemId = intent.getBundleExtra("commentBundle")?.getInt("problemId")

        val viewmodel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TestChildCommentViewModel::class.java].apply {
            setCommentId(commentId)
            setProblemId(problemId)
        }

        binding.viewModel = viewmodel
        binding.ideaRecyclerView.adapter = TestChildCommentAdapter()
        binding.ideaRecyclerView.setHasFixedSize(false)

        commentId?.let { id ->
            viewmodel.initChildCommentObject(id)
        }
    }
}