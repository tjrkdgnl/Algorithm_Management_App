package com.ama.algorithmmanagement.Activity.kDefault;

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.SearchProblemAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivitySearchBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.SearchViewModel
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-12
 * class : KSearchActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : SearchActivity 검색 API 를 이용한 검색화면
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
        val searchAdapter = SearchProblemAdapter()
        binding.rvSearchProblem.adapter = searchAdapter
        // editText 의 상태 즉 텍스트를 입력할때마다 네트워크 요청
        viewModel.inputSearch.observe(this,{
            // solved api 에서 "" 로 요청했을때 데이터가 던져줘서 막음
            val str = it.replace(" ","") // 데이터 없이 빈칸 "   " 이런식으로 보내도 기본 데이터 던져줘서 막음
            Timber.e("str: $str")
            if(str.isNotEmpty()){
                viewModel.callSearchQueryProblem(it)
            }else{
                searchAdapter.cleanData()
            }
        })

    }
}