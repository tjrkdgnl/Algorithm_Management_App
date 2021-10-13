package com.ama.algorithmmanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Model.KProblemsOfClass
import com.ama.algorithmmanagement.Network.KAPIGenerator
import kotlinx.coroutines.launch
import timber.log.Timber

class KDefaultCallViewModel : ViewModel() {
    private val _list = MutableLiveData<List<KProblemsOfClass>>()
    val list: LiveData<List<KProblemsOfClass>>
        get() = _list

    init {
        initList()
    }

    private fun initList() {
        viewModelScope.launch {
            try {
                _list.value = KAPIGenerator.getInstance().getProblemsOfClass()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

}