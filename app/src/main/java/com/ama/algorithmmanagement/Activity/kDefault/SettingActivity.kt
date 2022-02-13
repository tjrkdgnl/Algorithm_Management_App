package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.NoTipProblemsAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySettingBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.NoTipViewModel
import com.ama.algorithmmanagement.viewmodel.kDefault.SettingViewModel

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
    }
}