package com.ama.algorithmmanagement.presentation.status;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.data.model.Problems
import com.ama.algorithmmanagement.data.model.Stats
import com.ama.algorithmmanagement.domain.base.BaseRepository
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
class CategoryStatusViewModel(private val mRepository: BaseRepository) :ViewModel(){
    private val _userAllStats = MutableLiveData<Problems>()
    val userAllStats:LiveData<Problems>
        get()=_userAllStats

    private fun loadAllStats(){
        viewModelScope.launch {
            try{
                _userAllStats.value = mRepository.getSolvedProblems()
            }catch (e:Exception){
                Timber.e(e)
            }
        }
    }
    init{
       loadAllStats()
    }
}