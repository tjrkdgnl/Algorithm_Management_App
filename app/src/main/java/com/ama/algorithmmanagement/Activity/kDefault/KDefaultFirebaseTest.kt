package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.DefaultFirebaseTestBinding
import com.ama.algorithmmanagement.utils.BaseViewModelFactory
import com.ama.algorithmmanagement.viewmodel.test.FirebaseVIewModel

class KDefaultFirebaseTest :
    KBaseActivity<DefaultFirebaseTestBinding>(R.layout.default_firebase_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[FirebaseVIewModel::class.java]

        binding.viewModel = viewModel

        //mediatorLiveData Observer List에 등록
        viewModel.checkUserInfo.observe(this, {

        })

    }
}