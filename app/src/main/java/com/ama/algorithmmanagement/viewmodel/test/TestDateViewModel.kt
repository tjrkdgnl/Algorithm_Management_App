package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.data.model.DateInfo
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class TestDateViewModel(private val mRepository: BaseRepository) : ViewModel() {

    val dateList = ObservableArrayList<DateInfo>()

    var solvedProblemsCount: Int = 0


    fun saveDate() {
        viewModelScope.launch {
            try {
                mRepository.setDateInfo(solvedProblemsCount)

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getDateObj() {
        viewModelScope.launch {
            try {
                val dateObj = mRepository.getDateObject()

                dateObj?.let {
                    dateList.addAll(it.yearInfo[0].monthInfoList[0].dateList)
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}