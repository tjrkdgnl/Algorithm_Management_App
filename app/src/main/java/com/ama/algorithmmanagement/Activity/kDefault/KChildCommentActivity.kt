package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.test.TestChildCommentAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultChildcommentTestBinding
import com.ama.algorithmmanagement.viewmodel.test.TestChildCommentViewModel

class KChildCommentActivity :
    KBaseActivity<DefaultChildcommentTestBinding>(R.layout.default_childcomment_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val commentId = intent.getBundleExtra("commentBundle")?.getString("commentId")

        val viewmodel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TestChildCommentViewModel::class.java]

        binding.viewModel = viewmodel
        binding.ideaRecyclerView.adapter = TestChildCommentAdapter()
        binding.ideaRecyclerView.setHasFixedSize(false)

        commentId?.let { id ->
            viewmodel.initChildCommentObject(id)
        }
    }
}