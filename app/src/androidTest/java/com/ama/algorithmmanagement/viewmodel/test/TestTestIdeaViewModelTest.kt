package com.ama.algorithmmanagement.viewmodel.test

import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.data.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestTestIdeaViewModelTest {

    lateinit var testTestIdeaViewModel: TestIdeaViewModel

    @Before
    fun init() {
        val fakeSharedPreference = FakeSharedPreference()

        fakeSharedPreference.setUserId("Default_User")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()),
            DateUtils.getDate()
        )

        val fakeNetworkService =
            FakeNetworkService(FakeNetWorkDataProvider(ApplicationProvider.getApplicationContext()))

        testTestIdeaViewModel =
            TestIdeaViewModel(
                FakeRepository(
                    ApplicationProvider.getApplicationContext(),
                    fakeFirebaseReference,
                    fakeNetworkService,
                    fakeSharedPreference
                ), null
            )
    }


    @Test
    fun getIdeaInfos() {
        //given
//        testIdeaViewModelTest.setIdeaInfo(null, "dp를 이용하면 좋지 않을까?", 1111)


        //when
        val infos = testTestIdeaViewModel.ideaList

        assertEquals(infos[0].comment, "dp를 이용하면 좋지 않을까?")
        assertEquals(infos[0].url, null)
    }

    @Test
    fun getIdeaInfos_moreThanOne() {
        //given
        testTestIdeaViewModel.saveIdeaInfo()


        //when
        val infos = testTestIdeaViewModel.ideaList

        assertEquals(infos[0].comment, "dp를 이용하면 좋지 않을까?")
        assertEquals(infos[1].comment, "투 포인터 이용하자")
        assertEquals(infos[2].comment, "그래프를 이용하자")

    }
}