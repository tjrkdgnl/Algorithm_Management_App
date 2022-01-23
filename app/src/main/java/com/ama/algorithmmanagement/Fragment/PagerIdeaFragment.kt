package com.ama.algorithmmanagement.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultFragmentMainBinding
import com.ama.algorithmmanagement.databinding.FragmentIdeaBinding

class PagerIdeaFragment : KBaseFragment<FragmentIdeaBinding>(R.layout.fragment_idea) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.textviewDefault.text = getString(R.string.FragmentDefaultText)
    }

}