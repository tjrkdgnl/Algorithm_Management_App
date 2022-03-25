package com.ama.algorithmmanagement.presentation.signup.fragment

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
import com.ama.algorithmmanagement.databinding.FragmentConnectBojAccountBinding
import com.ama.algorithmmanagement.presentation.signup.view_model.KSignUpViewModel

/**
 * author : manyong Han
 * summary : 백준 연동하는 화면 프래그먼트
 */

class ConnectBOJAccountFragment() : KBaseFragment<FragmentConnectBojAccountBinding>(R.layout.fragment_connect_boj_account) {

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

    // 뷰모델을 부모꺼 사용하면서 값 변경해주는걸 알려주기만 하면 아래처럼 안해도됨
    // 때문에 뷰모델을 부모액티비티꺼를 사용한다? => 옵저빙이 액티비티에서 실행되면 된다.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = signUpViewModel

        signUpViewModel.isConnectSuccess.observe(viewLifecycleOwner) {
            if(it) {
                binding.connectNextBtn.visibility = View.VISIBLE
                signUpViewModel.isConnectSuccess.value = false
                signUpViewModel.isMoveToWebView.value = false
            }
        }
    }

    // requireActivity() 는 getActivity() 가 null 일 경우에 IllegalAccessException 을 던지기 때문에
    // 예외처리를 위해 사용하는 함수.
    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }
}