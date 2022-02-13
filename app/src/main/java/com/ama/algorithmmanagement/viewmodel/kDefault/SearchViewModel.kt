package com.ama.algorithmmanagement.viewmodel.kDefault;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.Problems

/**
 * @author : seungHo
 * @since : 2022-02-12
 * class : SearchViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class SearchViewModel(private val mRepository: BaseRepository):ViewModel() {
    val inputSearch = MutableLiveData<String>()

    val searchProblems = MutableLiveData<Problems>()
    private val _searchProblems:LiveData<Problems>
        get() = searchProblems

    // 검색 자동완성
    fun callSearchQueryProblem(query:String){

    }

}