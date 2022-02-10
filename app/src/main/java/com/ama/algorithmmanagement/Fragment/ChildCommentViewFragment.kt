package com.ama.algorithmmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ama.algorithmmanagement.Activity.AdapterListener
import com.ama.algorithmmanagement.Adapter.KChildCommentsAdapter
import com.ama.algorithmmanagement.Adapter.KCommentsAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentChildCommentViewBinding
import com.ama.algorithmmanagement.databinding.FragmentCommentViewBinding
import com.ama.algorithmmanagement.viewmodel.KViewProblemDetailViewModel
import timber.log.Timber

/**
 * author : manyong Han
 * summary : 대댓글 프래그먼트
 */
class ChildCommentViewFragment : KBaseFragment<FragmentChildCommentViewBinding>(R.layout.fragment_child_comment_view) {

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

        binding.viewModel = viewProblemDetailViewModel
        binding.recyclerviewChildCommentList.adapter = KChildCommentsAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }
}