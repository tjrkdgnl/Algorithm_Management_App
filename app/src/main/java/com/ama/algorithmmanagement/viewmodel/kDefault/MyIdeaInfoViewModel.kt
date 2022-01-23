package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MyIdeaInfoViewModel(private val mRepository: BaseRepository) :ViewModel() {

    private val _myIdeaInfo = MutableLiveData<IdeaObject>()
    val myIdeaList: LiveData<MutableList<IdeaInfos>> = Transformations.map(_myIdeaInfo) {
        it.ideaInfosList
    }

    init {
        getMyCommentList()
    }

    private fun getMyCommentList() {
        viewModelScope.launch {
            try {
                val lstTippingProblem = mRepository.getTippingProblem()?.problemInfoList
                if (lstTippingProblem != null) {
                    for (i in lstTippingProblem.indices) {
                        // todo - 넘겨줘야하는 인자가 Flow 활용 문이 있는거같아서 강휘에게 질문 필요
//                        mRepository.getIdeaInfos(lstTippingProblem[i].problem!!.problemId).collect {
//                            emit(it)
//                        }
                    }
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}