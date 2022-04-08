package com.ama.algorithmmanagement.presentation.vpdetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentCommentViewBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseFragment
import com.ama.algorithmmanagement.domain.entity.CommentInfo
import com.ama.algorithmmanagement.presentation.vpdetail.adapter.KCommentsAdapter
import com.ama.algorithmmanagement.presentation.vpdetail.view_model.KViewProblemDetailViewModel

/**
 * author : manyong Han
 * summary : 댓글 프래그먼트
 */

class CommentViewFragment(
    var childClickListener: (CommentInfo) -> Unit
) : KBaseFragment<FragmentCommentViewBinding>(R.layout.fragment_comment_view) {

    private lateinit var viewProblemDetailViewModel: KViewProblemDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewProblemDetailViewModel = ViewModelProvider(
            getViewModelStoreOwner(),
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[KViewProblemDetailViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewProblemDetailViewModel
        binding.recyclerviewCommentList.adapter = KCommentsAdapter(childClickListener)
        binding.recyclerviewCommentList.setHasFixedSize(false)
    }

    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }
}