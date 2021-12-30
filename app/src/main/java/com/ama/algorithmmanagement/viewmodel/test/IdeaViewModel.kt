package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.IdeaInfo
import com.ama.algorithmmanagement.Model.IdeaInfos
import kotlinx.coroutines.launch


class IdeaViewModel(private var repository: BaseRepository) : ViewModel() {
    var ideaList = ObservableArrayList<IdeaInfo>()
    var ideaLst = ObservableArrayList<IdeaInfo>()
    val comment = MutableLiveData<String>(null)
    var problemId: Int = 0
    var url: String? = null

    fun saveIdeaInfo() {
        viewModelScope.launch {
            repository.setIdeaInfo(url, comment.value, problemId)
        }
    }

    fun getIdeaInfos(problemId: Int) {
        viewModelScope.launch {
            val ideaInfos = repository.getIdeaInfos(problemId)

            ideaInfos?.let {
                ideaList.addAll(it.ideaList)
            }
        }
    }

}