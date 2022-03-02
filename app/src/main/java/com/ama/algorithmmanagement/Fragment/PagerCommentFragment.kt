package com.ama.algorithmmanagement.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.activity.kDefault.MyChildCommentActivity
import com.ama.algorithmmanagement.adapter.MyCommentAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseViewModelFactory
import com.ama.algorithmmanagement.base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentMyCommentBinding
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.viewmodel.kDefault.MyCommentViewModel
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