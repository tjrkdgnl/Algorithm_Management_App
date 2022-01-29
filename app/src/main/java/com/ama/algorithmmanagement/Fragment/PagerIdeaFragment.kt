package com.ama.algorithmmanagement.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultFragmentMainBinding
import com.ama.algorithmmanagement.databinding.FragmentIdeaBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.MyIdeaInfoViewModel

class PagerIdeaFragment : KBaseFragment<FragmentIdeaBinding>(R.layout.fragment_idea) {

    private lateinit var myIdeaInfoViewModel: MyIdeaInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myIdeaInfoViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[MyIdeaInfoViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = myIdeaInfoViewModel
    }

}