package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TipProblemInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class NoTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val noTipList = ObservableArrayList<TipProblemInfo>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        sharedPref.setUserId("skjh0818") // todo : 임시..
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