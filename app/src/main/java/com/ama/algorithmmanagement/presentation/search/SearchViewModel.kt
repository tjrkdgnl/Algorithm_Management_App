package com.ama.algorithmmanagement.presentation.search;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.AutoKeywordObject
import com.ama.algorithmmanagement.domain.entity.Keyword
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-12
 * class : SearchViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : SearchActivity.kt 뷰모델
 */
class SearchViewModel(private val mRepository: BaseRepository) : ViewModel() {
    // editText 양방향 바인딩
    val inputSearch = MutableLiveData<String>()

    // 검색결과 라이브데이터
    private val _searchProblems = MutableLiveData<AutoKeywordObject>()
    val searchProblems: LiveData<AutoKeywordObject>
        get() = _searchProblems

    private var debounceJob: Job? = null

    // 검색 자동완성 API 호출
    fun callSearchQueryProblem(query: String) {
        // debounce job 이 널이 아닐때 즉 실행되고 있다면 cancel
        // delay 600 이 끝나기도전에 callSearchQueryProblem 이 또 실행되면
        // debounceJob 에 의해 취소되고 600 이 끝나고 데이터에 세팅됨
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            try {
                delay(600)
                _searchProblems.value = mRepository.getAutoSearchedData(query)
            } catch (e: Exception) {
                Timber.e("error : $e")
            }
        }
    }

    fun clearSearchProblems() {
        // 검색결과가 0.6 초 뒤에 호출되기떄문에 입력창을 0.6 초 이내에 지울경우 코루틴 스코프가 실행되어 검색결과가 나옴
        debounceJob?.cancel()
        _searchProblems.value = AutoKeywordObject(
            keywords = listOf(),
            autocomplete = listOf(),
            problemCount = 0,
            searchedTags = listOf(),
            tagCount = 0,
            userCount = 0,
            users = listOf()
        )
    }
}