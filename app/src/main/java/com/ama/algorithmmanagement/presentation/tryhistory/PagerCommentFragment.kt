package com.ama.algorithmmanagement.presentation.tryhistory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentMyCommentBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseFragment
import com.ama.algorithmmanagement.domain.entity.CommentInfo
import com.ama.algorithmmanagement.presentation.tryhistory.adapter.MyCommentAdapter
import timber.log.Timber

class PagerCommentFragment(
    val problemId: Int?) : KBaseFragment<FragmentMyCommentBinding>(R.layout.fragment_my_comment) {

    private lateinit var myCommentViewModel: MyCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myCommentViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[MyCommentViewModel::class.java]

        myCommentViewModel.setProblemId(problemId)

        binding.viewModel = myCommentViewModel
        binding.rvMyComment.adapter = MyCommentAdapter(childClickListener)
        binding.rvMyComment.setHasFixedSize(false)
    }

    private val childClickListener :(CommentInfo) -> Unit = { commentInfo ->
        Timber.e("commentId : ${commentInfo.commentId}")

        val intent = Intent(context, MyChildCommentActivity::class.java)
        intent.putExtra("commentId", commentInfo.commentId)
        startActivity(intent)
    }

}