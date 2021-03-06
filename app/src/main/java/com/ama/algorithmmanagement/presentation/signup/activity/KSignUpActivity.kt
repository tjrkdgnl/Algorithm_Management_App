package com.ama.algorithmmanagement.presentation.signup.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.common.adapter.KViewPagerAdapter
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySignUpBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.presentation.login.activity.KRLoginActivity
import com.ama.algorithmmanagement.presentation.main.KMainActivity
import com.ama.algorithmmanagement.presentation.signup.fragment.ConnectBOJAccountFragment
import com.ama.algorithmmanagement.presentation.signup.fragment.RegisterFinalFragment
import com.ama.algorithmmanagement.presentation.signup.view_model.KSignUpViewModel
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber


/**
 * author : manyong Han
 * summary : 회원가입 베이스 액티비티
 */

class KSignUpActivity : KBaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var signUpViewModel: KSignUpViewModel
    private lateinit var viewPagerAdapter: KViewPagerAdapter
    private lateinit var fragments: List<Fragment>
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[KSignUpViewModel::class.java]

        binding.viewModel = signUpViewModel
        setFragment()
        getFcmToken()

        binding.connectWebView.apply {
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        viewPagerAdapter = KViewPagerAdapter(supportFragmentManager, fragments ,lifecycle)
        binding.signUpViewPager.adapter = viewPagerAdapter
        binding.signUpViewPager.isUserInputEnabled = false

        signUpViewModel.isGetFcmToken.observe(this) {
            if(it) {
                signUpViewModel.fcmToken.value = token
                signUpViewModel.isGetFcmToken.value = false
            }
        }

        signUpViewModel.isMoveToWebView.observe(this) {
            if(it) {
                binding.connectWebView.loadUrl("https://www.acmicpc.net/login?next=%2Fsso%3Fsso%3Dbm9uY2U9OWQ4NDkwYjY5ZGRmZGUxOGExYzE5ZWUzODk2MTUzMzg%253D%26sig%3D13d257578bcf76ef46b1b6c98ef563c3130f0e3b475c161a2e0efc46a81872d6%26redirect%3Dhttps%253A%252F%252Fsolved.ac%252Fapi%252Fv3%252Fauth%252Fsso%253Fprev%253D%25252F")
                binding.connectLayout.visibility = View.VISIBLE
                binding.signUpViewPager.visibility = View.GONE
            } else {
                getCookieToken(binding.connectWebView.url.toString())
                binding.connectWebView.loadUrl("")
                binding.connectLayout.visibility = View.GONE
                binding.signUpViewPager.visibility = View.VISIBLE
                binding.connectWebView.clearCache(true)
                binding.connectWebView.clearHistory()
                clearCookie()
            }
        }

        signUpViewModel.isGoToRegisterFinal.observe(this) {
            if(it) {
                moveToRegisterFinal()
                signUpViewModel.isGoToRegisterFinal.value = false
            }
        }

        signUpViewModel.isRegisterSuccess.observe(this) {
            if(it) {
                Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                successMovePage()
            } else {
                Toast.makeText(this, "계정이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        signUpViewModel.isMoveToBack.observe(this) {
            if(it) {
                val intent = Intent(this, KRLoginActivity::class.java)
                startActivity(intent)
                finish()
                signUpViewModel.isMoveToBack.value = false
            }
        }
    }

    private fun clearCookie(){
        try {
            val cookieManager = CookieManager.getInstance()
            cookieManager.removeSessionCookies({})
            cookieManager.removeAllCookies({})
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun getCookieToken(url: String) {
        val cookieManager = CookieManager.getInstance()
        val cookie = cookieManager.getCookie(url)

        val cookieList = cookie.split("; ")
        for (i in cookieList.indices) {
            if(cookieList[i].contains("solvedacToken")) {
                signUpViewModel.getSolvedacToken(cookieList[i])
                Timber.e(cookieList[i])
                break
            }
        }
    }

    private fun setFragment(){
        fragments = arrayListOf(
            ConnectBOJAccountFragment(),
            RegisterFinalFragment()
        )
    }

    // 백준 연동페이지로 다시 돌아갈수 있는 시나리오가 없으므로 고정값 2 할당
    // 메소드명 수정
    private fun moveToRegisterFinal() {
        binding.signUpViewPager.currentItem = 2
    }

    private fun successMovePage() {
        val intent = Intent(this, KMainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(!task.isSuccessful) {
                Timber.e("Fetching FCM registration Token failed")
            }

            token = task.result.toString()
            Timber.e(token)
        }
    }
}