package com.ama.algorithmmanagement.presentation.login.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityLoginBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.presentation.login.view_model.KRLoginViewModel
import com.ama.algorithmmanagement.presentation.main.KMainActivity
import com.ama.algorithmmanagement.presentation.signup.activity.KSignUpActivity

/**
 * author : manyong Han
 * summary : 로그인 화면 액티비티
 */
class KRLoginActivity : KBaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var loginViewModel: KRLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[KRLoginViewModel::class.java]

        binding.viewModel = loginViewModel

        loginViewModel.isLoginSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, KMainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "로그인에 실패 하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.isMoveToSignUp.observe(this) {
            if (it) {
                startActivity(Intent(this, KSignUpActivity::class.java))
                loginViewModel.isMoveToSignUp.value = false
            }
        }

        loginViewModel.isInputDataEmpty.observe(this) {
            if(!it) {
                Toast.makeText(this, "아이디, 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}