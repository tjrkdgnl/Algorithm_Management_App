package com.ama.algorithmmanagement.data.fake


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FakeFirebaseDateInfo {

    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var mUserId: String

    @Before
    fun init() {
        mUserId = "skjh0818"

        fakeFirebaseReference = FakeFirebaseReference(
            FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext())
        )
    }


    @Test
    fun getDateInfos() = runBlockingTest {
        //given
        fakeFirebaseReference.setDateInfo(mUserId, 0)

        //when
        val dateObject = fakeFirebaseReference.getDateObject(mUserId)


        //then
        assertEquals(dateObject?.userId, "skjh0818")
        assertEquals(dateObject?.yearInfo!![0].year, DateUtils.getYear())
        assertEquals(dateObject.yearInfo[0].monthInfoList[0].month, DateUtils.getMonth())
        assertEquals(dateObject.yearInfo[0].monthInfoList[0].count, 1)
        assertEquals(dateObject.yearInfo[0].monthInfoList[0].dateList[0].count, 0)
        assertEquals(dateObject.yearInfo[0].monthInfoList[0].dateList[0].date, DateUtils.getDate())
    }
}