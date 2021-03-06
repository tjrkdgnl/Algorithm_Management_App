package com.ama.algorithmmanagement.presentation.setting

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySettingBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import timber.log.Timber

/**
 * 설정 화면
 * author hongdroid94
 */
class SettingActivity : KBaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[SettingViewModel::class.java]
        binding.viewModel = settingViewModel

        binding.switchAutoLogin.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                settingViewModel.toggleAutoLoginCheck(isChecked)
            }
        }

        // set auto login switch
        settingViewModel.isAutoLogin.observe(this@SettingActivity) {
            binding.switchAutoLogin.isChecked = it
        }

        // set selected logout button
        settingViewModel.isSelectedLogout.observe(this@SettingActivity) {
            Timber.d(it.toString())
            if (it)
                finish()
        }
    }
}