package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.KAPIGenerator
import kotlinx.coroutines.launch
import timber.log.Timber

class KNoTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    private val _classList = MutableLiveData<List<KProblemsOfClass>>()
    val classList: LiveData<List<KProblemsOfClass>> get() = _classList

    private val _noTipAlgorithms = MutableLiveData<TippingProblemObject>()
    val noTipList: LiveData<MutableList<TipProblem>> = Transformations.map(_noTipAlgorithms) {
        it.problemList
    }

    init {
        getNotTippingProblem()
    }

    // request api using coroutine
    private fun initList() {
        viewModelScope.launch {
            try {
                _classList.value = KAPIGenerator.getInstance().getProblemsOfClass()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
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