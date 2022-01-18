package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.DateInfo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class TestDateViewModel(private val mRepository: BaseRepository) : ViewModel() {

    val dateList = ObservableArrayList<DateInfo>()

    fun saveDate() {
        viewModelScope.launch {
            try {
                mRepository.setDateInfo()

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getDateObj() {
        viewModelScope.launch {
            try {
                val dateObj = mRepository.getDateInfoObject()

                dateObj?.let {
                    dateList.addAll(it.dateList)
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}