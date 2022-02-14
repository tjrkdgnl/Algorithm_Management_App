package com.ama.algorithmmanagement.Activity.kDefault;

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import java.util.*

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

        // 참고 : https://stackoverflow.com/questions/12142021/how-can-i-do-something-0-5-seconds-after-text-changed-in-my-edittext-control
        binding.etInputSearch.addTextChangedListener(object: TextWatcher {
            // 타이머 객체생성
            var timer = Timer()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Timber.e("beforeTextChanged $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Timber.e("onTextChanged  $p0")
            }

            override fun afterTextChanged(p0: Editable?) {
                timer.cancel()// 취소 (예약된 작업도 취소함)
                timer = Timer() // 타이머객체 다시만들고
                // 공백이 들어간상태에선 요청 막음
                if(p0.toString().isNotEmpty() && p0.toString().isNotBlank()){
                    timer.schedule(object:TimerTask(){
                        override fun run() {
                            viewModel.callSearchQueryProblem(p0.toString())
                        }

                    },600) // 0.6초 뒤에 예약할작업 지정
                }
                // 즉 schedule 에서 0.6 초 대기시키고 run 안에있는 함수를 실행시키는데
                // afterTextChanged 첫줄에 cancel- 함수가 있어 0.6 초 이내에 값이 바뀔경우 예약된 작업도 취소됨
            }
        })
    }
}