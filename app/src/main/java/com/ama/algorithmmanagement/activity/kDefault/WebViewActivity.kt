package com.ama.algorithmmanagement.activity.kDefault

import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseViewModelFactory
import com.ama.algorithmmanagement.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityWebViewBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.WebViewViewModel

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