package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.DisplayName
import com.ama.algorithmmanagement.model.Tag
import com.ama.algorithmmanagement.model.TaggedProblem
import com.ama.algorithmmanagement.model.TipProblemInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class MyTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val myTipList = ObservableArrayList<TipProblemInfo>()
    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        sharedPref.setUserId("seungho0510") // todo : 임시..
        getMyTippingProblem()
    }

    private fun getMyTippingProblem() {
        viewModelScope.launch {
            try {
                val list = mRepository.getTippingProblem()?.problemInfoList
                Timber.d(list.toString())
                if (list != null)
                    myTipList.addAll(list)
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }


    private fun setMyTippingProblem() {
        // todo - for test (추후 제거)
        viewModelScope.launch {
            try {
                val taggedProblem =
                    TaggedProblem(
                        1000, "A+B", true, false, 151801,  1, 17, true,  2.333,
                        mutableListOf(
                            Tag(
                                "arithmetic",
                                false,
                                121,
                                494,
                                mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                            )
                        )
                    )

                mRepository.setTippingProblem(taggedProblem, false, "hong chul good")

//                val problem = mRepository.getProblem(1111)
//                mRepository.setTippingProblem(problem, false, "재귀를 사용하면 좋다")

            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }

    }
}