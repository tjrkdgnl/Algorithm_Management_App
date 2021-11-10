package com.ama.algorithmmanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KLoginViewModel : ViewModel() {
    //two-way databinding은 xml에서 LiveData가 아닌 MutableLiveData를 사용한다
    val userId = MutableLiveData<String>()


}