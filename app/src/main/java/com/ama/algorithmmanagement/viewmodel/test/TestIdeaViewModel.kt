package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import timber.log.Timber


class TestIdeaViewModel(private var repository: BaseRepository) {

    fun setIdeaInfo(url: String?, comment: String?, problemId: Int) {
        repository.setIdeaInfo(url, comment, problemId).onSuccess {

        }.onFailure {
            Timber.e(it)
        }
    }

    fun getIdeaInfos(problemId:Int){
        repository.getIdeaInfos(problemId)
    }

}