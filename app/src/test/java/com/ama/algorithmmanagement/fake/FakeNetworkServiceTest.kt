package com.ama.algorithmmanagement.fake

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FakeNetworkServiceTest {

    lateinit var mFakeNetworkService: FakeNetworkService
    lateinit var mFakeSharedPreference: FakeSharedPreference


    @Before
    fun init() {
        mFakeSharedPreference = FakeSharedPreference()
        mFakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider())

        mFakeSharedPreference.setUserId("skjh0818")

    }


    @Test
    fun getSolvedProblems() = runBlockingTest {
        //when
        val userId = mFakeSharedPreference.getUserId()
        val solvedProblems = mFakeNetworkService.getSolvedProblems(userId!!)

        //then
        assertEquals(solvedProblems.problemList?.size, 30)
        assertEquals(solvedProblems.problemList?.get(0)?.votedUserCount, 17)
        assertEquals(solvedProblems.problemList?.get(0)?.isSolvable, true)
        assertEquals(solvedProblems.problemList?.get(0)?.titleKo, "A+B")

    }


    @Test
    fun getSearchProblemList() = runBlockingTest {
        //when
        val problems = mFakeNetworkService.getSearchProblemList(1111)

        //then
        assertEquals(problems.problemList?.size, 30)
        assertEquals(problems.problemList?.get(0)?.titleKo, "A+B")
        assertEquals(problems.problemList?.get(0)?.problemId, 1111)

        assertEquals(problems.problemList?.get(1)?.titleKo, "A+B")
        assertEquals(problems.problemList?.get(1)?.problemId, 1112)

    }

    @Test
    fun getUserStats() = runBlockingTest {
        //when
        val userid = mFakeSharedPreference.getUserId()
        val userStats = mFakeNetworkService.getUserStats(userid!!)

        //then
        assertEquals(userStats[0].level, 0)
        assertEquals(userStats[0].total, 116)
        assertEquals(userStats[0].solved, 5)
        assertEquals(userStats[0].partial, 0)
        assertEquals(userStats[0].tried, 0)

        assertEquals(userStats[1].level, 1)
        assertEquals(userStats[1].total, 117)
        assertEquals(userStats[1].solved, 6)
        assertEquals(userStats[1].partial, 0)
        assertEquals(userStats[1].tried, 1)

    }

    @Test
    fun getBOJUserInfo() = runBlockingTest {
        //when
        //solvedToken을 셋팅하는 작업이 필요함
        val bojUser = mFakeNetworkService.getBOJUserInfo()

        //then
        assertEquals(bojUser[0].id, 1000)
        assertEquals(bojUser[0].status, "solved")

        assertEquals(bojUser[14].id, 1014)
        assertEquals(bojUser[14].status, "tried")
    }
}