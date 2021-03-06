package com.ama.algorithmmanagement.presentation.notip

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class NoTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val noTipList = ObservableArrayList<TipProblemInfo>()

    init {
        getNotTippingProblem()
    }

    private fun getNotTippingProblem() {
        viewModelScope.launch {
            try {
                val list = mRepository.getNotTippingProblem()?.problemInfoList
                noTipList.addAll(list!!)
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}