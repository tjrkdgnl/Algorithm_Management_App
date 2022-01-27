package com.ama.algorithmmanagement.viewmodel.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestTipViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var testTipViewModel: TestTipViewModel
    lateinit var mFakeNetworkService: FakeNetworkService

    @Before
    fun init() {
        val fakeSharedPreference = FakeSharedPreference()

        fakeSharedPreference.setUserId("Default_User")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()), DateUtils.getDate()
        )

        mFakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider(ApplicationProvider.getApplicationContext()))

        testTipViewModel = TestTipViewModel(
            FakeRepository(
                ApplicationProvider.getApplicationContext(),
                fakeFirebaseReference,
                mFakeNetworkService,
                fakeSharedPreference
            )
        )
    }

    @Test
    fun getNotTippingProblem(){
        //when
        val obj = testTipViewModel.getNottipProblemObject()

        assertEquals(obj!!.problemInfoList[0].tipComment, null)
        assertEquals(obj.problemInfoList[0].isShow, false)
        assertEquals(obj.problemInfoList[0].problem.problemId, 1010)
        assertEquals(obj.problemInfoList[0].problem.titleKo, "A+B")
        assertEquals(obj.problemInfoList[0].problem.level, 11)

    }

    @Test
    fun setTippingProblem() = runBlocking {
        //given
        val problem = mFakeNetworkService.getProblem(1111)

//        when
        testTipViewModel.setTippingProblem(problem, false, "재귀를 사용하면 좋다")

        testTipViewModel.getTipProblemObject()

        val lst = testTipViewModel.tipProbleObject?.problemInfoList!!

        //then
        assertEquals(lst[0].tipComment, "재귀를 사용하면 좋다")
        assertEquals(lst[0].isShow, false)
        assertEquals(lst[0].problem.problemId, 1111)
        assertEquals(lst[0].problem.titleKo, "A+B")
        assertEquals(lst[0].problem.level, 1)

    }

    @Test
    fun setTippingProblem_moreThanOne() = runBlocking {
        //given
        val problem = mFakeNetworkService.getProblem(1111)
        val problem2 = mFakeNetworkService.getProblem(2222)
        val problem3 = mFakeNetworkService.getProblem(3333)

        //when
        testTipViewModel.setTippingProblem(problem, false, "재귀를 사용하면 좋다")
        testTipViewModel.setTippingProblem(problem2, true, "그래프 사용하면 좋다")
        testTipViewModel.setTippingProblem(problem3, true, "투포인터 사용하면 좋다")

        val lst = testTipViewModel.tipProblems

        //then
        assertEquals(lst[0].tipComment, "재귀를 사용하면 좋다")
        assertEquals(lst[0].isShow, false)
        assertEquals(lst[0].problem.problemId, 1111)
        assertEquals(lst[0].problem.titleKo, "A+B")
        assertEquals(lst[0].problem.level, 1)

        assertEquals(lst[1].tipComment, "그래프 사용하면 좋다")
        assertEquals(lst[1].isShow, true)
        assertEquals(lst[1].problem.problemId, 2222)
        assertEquals(lst[1].problem.titleKo, "A+B")
        assertEquals(lst[1].problem.level, 1)

        assertEquals(lst[2].tipComment, "투포인터 사용하면 좋다")
        assertEquals(lst[2].isShow, true)
        assertEquals(lst[2].problem.problemId, 3333)
        assertEquals(lst[2].problem.titleKo, "A+B")
        assertEquals(lst[2].problem.level, 1)

    }

}