package com.ama.algorithmmanagement.viewmodel.test

import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.data.fake.FakeNetWorkDataProvider
import com.ama.algorithmmanagement.data.fake.FakeNetworkService
import com.ama.algorithmmanagement.data.fake.FakeSharedPreference
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeSharedPreferenceTest {
    lateinit var mFakeNetworkService: FakeNetworkService
    lateinit var mFakeSharedPref: FakeSharedPreference

    @Before
    fun init() {
        mFakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider(ApplicationProvider.getApplicationContext()))
        mFakeSharedPref = FakeSharedPreference()
    }

    @Test
    fun setSolvedProblems() = runBlocking {
        //given
        val problems = mFakeNetworkService.getSolvedProblems("skjh0818")

        //when
        //obj - > json
        mFakeSharedPref.setSolvedProblems(problems)

        //then
        //json -> obj
        val data = mFakeSharedPref.getSolvedProblems()

        assertEquals(data?.problemList?.get(0)?.titleKo, "A+B")
        assertEquals(data?.problemList?.get(0)?.problemId, 1000)
    }


}