package com.ama.algorithmmanagement.presentation.mytip

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class MyTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val myTipList = ObservableArrayList<TipProblemInfo>()

    init {
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