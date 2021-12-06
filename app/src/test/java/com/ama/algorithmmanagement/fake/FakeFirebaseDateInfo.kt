package com.ama.algorithmmanagement.fake


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.Model.DateInfo
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FakeFirebaseDateInfo {

    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserIdToLocal("skjh0818")

        fakeFirebaseReference =
            FakeFirebaseReference(
                ApplicationProvider.getApplicationContext(),
                FakeFirebaseDataProvider(),
                fakeSharedPreference
            )
    }


    @Test
    fun getDateInfos() {
        fakeFirebaseReference.setDateInfo()

        val dateInfos = fakeFirebaseReference.getDateInfos()

        val dateInfoList = mutableListOf(DateInfo("2021.12.01"))

        assertEquals(dateInfos?.count, 1)
        assertEquals(dateInfos?.userId, "skjh0818")
        assertEquals(dateInfos?.dateList?.get(0), dateInfoList.get(0))
    }
}