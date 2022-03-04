package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.*
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.data.model.AutoKeywordObject
import com.ama.algorithmmanagement.data.model.Keyword
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class SearchTestViewModel(private val repository: BaseRepository) : ViewModel() {
    val inputKeyword = MutableLiveData<String>(null)
    private val keywords = MutableLiveData<AutoKeywordObject>()
    val _keywords: LiveData<List<Keyword>>
        get() = Transformations.map(keywords) {
            it.keywords
        }

    fun loadSearchData() {
        viewModelScope.launch {
            try {
                delay(1000)
                inputKeyword.value?.let { keyword ->
                    if(keyword.isNotEmpty())
                        keywords.value = repository.getAutoSearchedData(keyword)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}