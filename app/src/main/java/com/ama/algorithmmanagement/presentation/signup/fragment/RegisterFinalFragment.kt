package com.ama.algorithmmanagement.presentation.signup.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentRegisterFinalBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseFragment
import com.ama.algorithmmanagement.presentation.signup.view_model.KSignUpViewModel
import com.ama.algorithmmanagement.utils.KeyboardUtil

/**
 * author : manyong Han
 * summary : 회원가입 마지막 화면 프래그먼트 (아이디, 비밀번호 입력)
 */

class RegisterFinalFragment : KBaseFragment<FragmentRegisterFinalBinding>(R.layout.fragment_register_final) {

    private lateinit var signUpViewModel: KSignUpViewModel
    private var amaUseCheck: Boolean = false
    private var amaPersonalCheck: Boolean = false

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
        binding.registerUserPwEdit.setOnKeyListener(keyListener)

        signUpViewModel.isUseTermsChecked.observe(viewLifecycleOwner) {
            amaUseCheck = it
            if(it) {
                if (signUpViewModel.isPersonalTermsChecked.value == true) {
                    binding.termsAllCheck.isChecked = true

                    binding.registerBtn.isEnabled = true
                    binding.registerBtn.setBackgroundColor(ContextCompat.getColor(AMAApplication.INSTANCE, R.color.deep_green))
                    binding.registerBtn.setTextColor(ContextCompat.getColor(AMAApplication.INSTANCE, R.color.white_text))
                }
            } else {
                binding.termsAllCheck.isChecked = false
                binding.personalTermsLayoutCheck.isChecked = amaPersonalCheck

                binding.registerBtn.isEnabled = false
                binding.registerBtn.setBackgroundColor(
                    ContextCompat.getColor(
                        AMAApplication.INSTANCE,
                        R.color.gray_background
                    )
                )
                binding.registerBtn.setTextColor(
                    ContextCompat.getColor(
                        AMAApplication.INSTANCE,
                        R.color.hint_text
                    )
                )
            }
        }

        signUpViewModel.isPersonalTermsChecked.observe(viewLifecycleOwner) {
            amaPersonalCheck = it
            if (it) {
                if (signUpViewModel.isUseTermsChecked.value == true) {
                    binding.termsAllCheck.isChecked = true

                    binding.registerBtn.isEnabled = true
                    binding.registerBtn.setBackgroundColor(
                        ContextCompat.getColor(
                            AMAApplication.INSTANCE,
                            R.color.deep_green
                        )
                    )
                    binding.registerBtn.setTextColor(
                        ContextCompat.getColor(
                            AMAApplication.INSTANCE,
                            R.color.white_text
                        )
                    )
                }
            } else {
                binding.termsAllCheck.isChecked = false
                binding.registerUseTermsCheck.isChecked = amaUseCheck

                binding.registerBtn.isEnabled = false
                binding.registerBtn.setBackgroundColor(
                    ContextCompat.getColor(
                        AMAApplication.INSTANCE,
                        R.color.gray_background
                    )
                )
                binding.registerBtn.setTextColor(
                    ContextCompat.getColor(
                        AMAApplication.INSTANCE,
                        R.color.hint_text
                    )
                )
            }
        }

        binding.termsAllCheck.setOnClickListener {
            val onChecked = binding.termsAllCheck.isChecked
            binding.registerUseTermsCheck.isChecked = onChecked
            binding.personalTermsLayoutCheck.isChecked = onChecked
        }
    }

    // requireActivity() 는 getActivity() 가 null 일 경우에 IllegalAccessException 을 던지기 때문에
    // 예외처리를 위해 사용하는 함수.
    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }

    private val keyListener = View.OnKeyListener { _, p1, p2 ->
        if(p2.action == KeyEvent.ACTION_DOWN && p1 == KeyEvent.KEYCODE_ENTER){
            KeyboardUtil.hideKeyboard(context, requireActivity())

            binding.registerUserPwEdit.clearFocus()
            binding.registerUserPwEdit.isCursorVisible = false
            true
        } else {
            false
        }
    }
}