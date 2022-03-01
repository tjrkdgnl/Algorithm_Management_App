package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityWebViewBinding
import com.ama.algorithmmanagement.viewmodel.WebViewViewModel
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-14
 * class : WebViewActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 웹뷰 액티비티
 */
class WebViewActivity : KBaseActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {
    private lateinit var viewModel: WebViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // intent 에서 전달받은값 받아서와서 url 을 뿌려줌
        val getUrl = intent.getIntExtra("problemId", 1000)
        viewModel = ViewModelProvider(
            this, BaseViewModelFactory(
                RepositoryLocator().getRepository(AMAApplication.INSTANCE)
            )
        )[WebViewViewModel::class.java]
        binding.viewmodel = viewModel
        viewModel.setProblemId(getUrl)
        binding.webview.webChromeClient = WebChromeClient()
        viewModel.problemId.observe(this,{
            binding.webview.loadUrl("https://www.acmicpc.net/problem/$it")
        })
        viewModel.isFinishActivity.observe(this,{ isFinish->
            if(isFinish){
                finish()
            }
        })
    }
}