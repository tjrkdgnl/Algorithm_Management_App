package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.KAPIGenerator
import kotlinx.coroutines.launch
import timber.log.Timber

class KNoTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    private val _noTipAlgorithms = MutableLiveData<TippingProblemObject>()
    val noTipList: LiveData<MutableList<TipProblem>> = Transformations.map(_noTipAlgorithms) {
        it.problemList
    }

    init {
        getNotTippingProblem()
    }

    private fun getNotTippingProblem() {
        viewModelScope.launch {
            try {
                _noTipAlgorithms.value = mRepository.getNotTippingProblem()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}