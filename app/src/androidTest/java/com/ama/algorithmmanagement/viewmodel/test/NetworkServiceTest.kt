package com.ama.algorithmmanagement.viewmodel.test

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkServiceTest {

    lateinit var fakeRepository: FakeRepository
    var mUserId: String? = null

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val fakeSharedPreference = FakeSharedPreference()
        val fakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider())

        fakeSharedPreference.setUserId("skjh0818")

        mUserId = fakeSharedPreference.getUserId()

        val fakeFirebaseReference =
            FakeFirebaseReference(FakeFirebaseDataProvider(), DateUtils.createDate())

        fakeRepository =
            FakeRepository(context, fakeFirebaseReference, fakeNetworkService, fakeSharedPreference)
    }


    @Test
    fun getSearchProblemList() = runBlocking {
        //then
        val lst = fakeRepository.getSearchProblemList(1111)

        assertEquals(lst.problemList?.size, 30)
        assertEquals(lst.problemList?.get(0)?.problemId, 1111)
        assertEquals(lst.problemList?.get(0)?.level, 1)
        assertEquals(lst.problemList?.get(0)?.titleKo, "A+B")
    }


    @Test
    fun getUserStats() = runBlocking {
        //then
        val stats = fakeRepository.getUserStats()

        assertEquals(stats[0].level, 0)
        assertEquals(stats[0].total, 116)
        assertEquals(stats[0].solved, 5)
        assertEquals(stats[0].partial, 0)
        assertEquals(stats[0].tried, 0)

    }

    @Test
    fun getBOJInfo() = runBlocking {
        //then
        val lst = fakeRepository.getBOJUserInfo()

        assertEquals(lst[0].status, "solved")
        assertEquals(lst[0].id, 1000)
        assertEquals(lst[14].status, "tried")
        assertEquals(lst[14].id, 1014)

    }
}