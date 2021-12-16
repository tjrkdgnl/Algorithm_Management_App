package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import timber.log.Timber


class TestIdeaViewModel(private var repository: BaseRepository) {
    var ideaInfos: IdeaInfos? = null
    var ideaLst = ObservableArrayList<IdeaInfo>()

    fun setIdeaInfo(url: String?, comment: String?, problemId: Int) {
        repository.setIdeaInfo(url, comment, problemId).onSuccess {

        }.onFailure {
            Timber.e(it)
        }
    }

    fun getIdeaInfos(problemId: Int) {
        ideaInfos = repository.getIdeaInfos(problemId)

        ideaInfos?.let { infos ->
            ideaLst.addAll(infos.ideaList)
        }
    }

}