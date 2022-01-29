package com.ama.algorithmmanagement.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.CommentListAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentCommentBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.MyCommentViewModel

class PagerCommentFragment : KBaseFragment<FragmentCommentBinding>(R.layout.fragment_comment) {

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = myCommentViewModel
        binding.rvMyComment.adapter = CommentListAdapter()
        binding.rvMyComment.setHasFixedSize(false)
    }
}