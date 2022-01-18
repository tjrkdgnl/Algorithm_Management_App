package com.ama.algorithmmanagement.Activity.kDefault

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.test.CommentAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultCommentTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestCommentViewModel

class KDefaultCommentActivity :
    KBaseActivity<DefaultCommentTestBinding>(R.layout.default_comment_test) {

    val moveToChild: (String) -> Unit = { commentId ->
        val intent = Intent(applicationContext, KChildCommentActivity::class.java)
        val bundle = Bundle()
        bundle.putString("commentId", commentId)

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
        binding.ideaRecyclerView.adapter = CommentAdapter(moveToChild)
        binding.ideaRecyclerView.setHasFixedSize(false)
    }
}