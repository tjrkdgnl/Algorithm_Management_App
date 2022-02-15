package com.ama.algorithmmanagement.Activity.kDefault

import android.os.Bundle
import androidx.lifecycle.ViewModelStore
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ActivityWebViewBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel

/**
 * @author : seungHo
 * @since : 2022-02-14
 * class : WebViewActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 웹뷰 액티비티
 */
class WebViewActivity : KBaseActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // intent 에서 전달받은값 받아서와서 url 을 뿌려줌
        val getUrl = intent.getIntExtra("problemId", 1000)
        binding.webview.loadUrl("https://www.acmicpc.net/problem/$getUrl")
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}