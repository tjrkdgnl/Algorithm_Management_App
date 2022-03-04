package com.ama.algorithmmanagement.activity.test

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.test.TestCommentAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultCommentTestBinding
import com.ama.algorithmmanagement.data.model.CommentInfo
import com.ama.algorithmmanagement.viewmodel.test.TestCommentViewModel

class TestCommentActivity :
    KBaseActivity<DefaultCommentTestBinding>(R.layout.default_comment_test) {

    val moveToChild: (CommentInfo) -> Unit = { obj ->
        val intent = Intent(applicationContext, TestChildCommentActivity::class.java)
        val bundle = Bundle()
        bundle.putString("commentId", obj.commentId)
        bundle.putInt("problemId", obj.problemId)

        intent.putExtra("commentBundle", bundle)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TestCommentViewModel::class.java]

        binding.viewModel = viewModel
        binding.ideaRecyclerView.adapter = TestCommentAdapter(moveToChild)
        binding.ideaRecyclerView.setHasFixedSize(false)
    }
}