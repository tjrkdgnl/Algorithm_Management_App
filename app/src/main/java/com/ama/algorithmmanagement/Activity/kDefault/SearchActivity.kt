package com.ama.algorithmmanagement.Activity.kDefault;

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySearchBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.SearchViewModel

/**
 * @author : seungHo
 * @since : 2022-02-12
 * class : KSearchActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class SearchActivity : KBaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[SearchViewModel::class.java]
        binding.viewmodel = viewModel

    }
}