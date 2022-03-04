package com.ama.algorithmmanagement.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentRegisterFinalBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.KSignUpViewModel

/**
 * author : manyong Han
 * summary : 회원가입 마지막 화면 프래그먼트 (아이디, 비밀번호 입력)
 */

class RegisterFinalFragment : KBaseFragment<FragmentRegisterFinalBinding>(R.layout.fragment_register_final) {

    private lateinit var signUpViewModel: KSignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpViewModel = ViewModelProvider(
            getViewModelStoreOwner(),
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[KSignUpViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = signUpViewModel
    }

    // requireActivity() 는 getActivity() 가 null 일 경우에 IllegalAccessException 을 던지기 때문에
    // 예외처리를 위해 사용하는 함수.
    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }
}