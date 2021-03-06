package com.ama.algorithmmanagement.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("Livedata value was never set")
        }
    } finally {
        this.removeObserver(observer)
    }

    return data as T
}

 fun <T, K, R> combineWith(
    firstLiveData: LiveData<T>,
    secondLiveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()

    result.addSource(firstLiveData) {
        result.value = block(firstLiveData.value, secondLiveData.value)
    }

    result.addSource(secondLiveData) {
        result.value = block(firstLiveData.value, secondLiveData.value)
    }

    return result
}
