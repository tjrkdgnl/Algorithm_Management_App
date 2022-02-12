package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TipProblemInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class MyTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val myTipList = ObservableArrayList<TipProblemInfo>()
    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
//        sharedPref.setUserId("skjh0818") // todo : 임시..
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
}