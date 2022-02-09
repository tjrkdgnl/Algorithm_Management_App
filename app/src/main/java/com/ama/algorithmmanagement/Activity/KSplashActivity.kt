package com.ama.algorithmmanagement.Activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySplashBinding
import com.ama.algorithmmanagement.viewmodel.KSplashViewModel
<<<<<<< Updated upstream
/**
 * author : manyong Han
 * summary : 스플래시 액티비티
 */
=======

>>>>>>> Stashed changes
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
                startActivity(Intent(this, KViewProblemDetailActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, KRLoginActivity::class.java))
                finish()
            }
        }

    }

}