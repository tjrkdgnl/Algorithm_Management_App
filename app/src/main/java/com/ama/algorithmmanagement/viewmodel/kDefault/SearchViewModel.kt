package com.ama.algorithmmanagement.viewmodel.kDefault;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.AutoKeywordObject
import com.ama.algorithmmanagement.model.Problems
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
class SearchViewModel(private val mRepository: BaseRepository):ViewModel() {
    // editText 양방향 바인딩
    val inputSearch = MutableLiveData<String>()

    // 검색결과 라이브데이터
    private val _searchProblems = MutableLiveData<AutoKeywordObject>()
    val searchProblems:LiveData<AutoKeywordObject>
        get() = _searchProblems

    // 검색 자동완성 API 호출
    fun callSearchQueryProblem(query:String){
        viewModelScope.launch {
            try{
                _searchProblems.value = mRepository.getAutoSearchedData(query)
            }catch (e:Exception){
                Timber.e("error : $e")
            }
        }

    }

}