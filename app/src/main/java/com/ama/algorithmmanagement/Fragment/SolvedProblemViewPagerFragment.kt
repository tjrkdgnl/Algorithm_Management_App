package com.ama.algorithmmanagement.Fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentNewSolvedProblemBinding
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary : viewpager2 팁 작성 뷰페이져(프래그먼트 어댑터사용) 프레그먼트
 */
class SolvedProblemViewPagerFragment :
    KBaseFragment<FragmentNewSolvedProblemBinding>(R.layout.fragment_new_solved_problem) {
    private lateinit var viewModel: NewSolvedProblemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            getViewModelStoreOwner(),
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[NewSolvedProblemViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewmodel = viewModel
    }

    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }
}