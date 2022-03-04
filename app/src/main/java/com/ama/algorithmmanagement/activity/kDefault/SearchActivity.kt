package com.ama.algorithmmanagement.activity.kDefault;

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.adapter.SearchProblemAdapter
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
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

        binding.etInputSearch.onChangeDebounceOption()
    }

    private fun EditText.onChangeDebounceOption() {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Timber.e("beforeTextChanged $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Timber.e("onTextChanged  $p0")
            }

            override fun afterTextChanged(p0: Editable?) {
                // 공백이 들어간상태에선 요청 막음
                if (p0.toString().isNotEmpty() && p0.toString().isNotBlank()) {
                    viewModel.callSearchQueryProblem(p0.toString())
                }
            }

        })
    }
}