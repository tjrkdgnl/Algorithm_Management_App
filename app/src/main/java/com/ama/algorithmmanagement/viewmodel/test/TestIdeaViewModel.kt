package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.IdeaInfo
import com.ama.algorithmmanagement.Model.IdeaInfos


class TestIdeaViewModel(private var repository: BaseRepository) {
    var ideaInfos: IdeaInfos? = null
    var ideaLst = ObservableArrayList<IdeaInfo>()

    fun setIdeaInfo(url: String?, comment: String?, problemId: Int) {
        val idea = repository.setIdeaInfo(url, comment, problemId)
        ideaLst.add(idea)
    }

    fun getIdeaInfos(problemId: Int) {
        ideaInfos = repository.getIdeaInfos(problemId)

        ideaInfos?.let { infos ->
            ideaLst.addAll(infos.ideaList)
        }
    }

}