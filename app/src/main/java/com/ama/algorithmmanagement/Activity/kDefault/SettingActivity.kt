package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySettingBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.SettingViewModel
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
        settingViewModel.isAutoLogin.observe(this@SettingActivity, {
            binding.switchAutoLogin.isChecked = it
        })

        // set selected logout button
        settingViewModel.isSelectedLogout.observe(this@SettingActivity, {
            Timber.d(it.toString())
            if(it) {
                // todo - 로그인 액티비티로 이동 되는 ux 시나리오를 논의 후 구현 해야할 듯 함..
//                Intent(this@SettingActivity, LoginActivit)
            } else {

            }
        })
    }
}