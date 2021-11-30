package com.ama.algorithmmanagement.Activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.Fragment.MainFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultActivityMainBinding
import com.ama.algorithmmanagement.viewmodel.KDefaultMainViewModel

class KDefaultActivity : KBaseActivity<DefaultActivityMainBinding>(R.layout.default_activity_main) {
    private lateinit var mainViewModel: KDefaultMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(KDefaultMainViewModel::class.java)
        binding.viewModel = mainViewModel;

        supportFragmentManager.beginTransaction().add(R.id.frameLayout_default, MainFragment())
            .commit()

        mainViewModel.moveToCallAPIAct.observe(this, {
            if (it) {
                startActivity(Intent(this, KCallAPIActivity::class.java))
                mainViewModel.moveToCallAPIAct.value = false
            }
        })

        mainViewModel.moveToSolvedAct.observe(this, {
            if (it) {
                startActivity(Intent(this, KCallSolvedAlgorithmAct::class.java))
                mainViewModel.moveToCallAPIAct.value = false
            }
        })

        mainViewModel.moveToLoginAct.observe(this, {
            if (it) {
                startActivity(Intent(this, KLoginActivity::class.java))
                mainViewModel.moveToLoginAct.value = false
            }
        })
    }
}