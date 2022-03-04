package com.ama.algorithmmanagement.activity.kDefault

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySplashBinding
import com.ama.algorithmmanagement.presentation.main.KMainActivity
import com.ama.algorithmmanagement.viewmodel.kDefault.KSplashViewModel

/**
 * author : manyong Han
 * summary : 스플래시 액티비티
 */
class KSplashActivity : KBaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private lateinit var splashViewModel: KSplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[KSplashViewModel::class.java]

        binding.viewModel = splashViewModel

        splashViewModel.isGoToMain.observe(this) {
            if(it) {
                startActivity(Intent(this, KMainActivity::class.java))
            } else {
                startActivity(Intent(this, KRLoginActivity::class.java))
            }
            finish()
        }
    }
}