package com.ama.algorithmmanagement.fake


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.Model.DateInfo
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FakeFirebaseDateInfo {

    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference
    lateinit var mUserId: String

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserId("skjh0818")

        mUserId = fakeSharedPreference.getUserId()!!

        fakeFirebaseReference =
            FakeFirebaseReference(
                FakeFirebaseDataProvider(),
                DateUtils.createDate()
            )
    }


    @Test
    fun getDateInfos() {
        //given
        fakeFirebaseReference.setDateInfo(mUserId)

        //when
        val dateInfos = fakeFirebaseReference.getDateInfos(mUserId)
        val dateInfoList = mutableListOf(DateInfo(DateUtils.createDate()))

        //then
        assertEquals(dateInfos?.count, 1)
        assertEquals(dateInfos?.userId, "skjh0818")
        assertEquals(dateInfos?.dateList?.get(0), dateInfoList.get(0))
    }
}