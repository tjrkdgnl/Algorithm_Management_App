package com.ama.algorithmmanagement.viewmodel.test

import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestIdeaViewModelTest {

    lateinit var testIdeaViewModel: IdeaViewModel

    @Before
    fun init() {
        val fakeSharedPreference = FakeSharedPreference()

        fakeSharedPreference.setUserId("Default_User")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()), DateUtils.createDate()
        )

        val fakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider(ApplicationProvider.getApplicationContext()))

        testIdeaViewModel =
            IdeaViewModel(
                FakeRepository(
                    ApplicationProvider.getApplicationContext(),
                    fakeFirebaseReference,
                    fakeNetworkService,
                    fakeSharedPreference
                )
            )
    }


    @Test
    fun getIdeaInfos() {
        //given
//        testIdeaViewModelTest.setIdeaInfo(null, "dp를 이용하면 좋지 않을까?", 1111)
        testIdeaViewModel.getIdeaInfos(1111)

        //when
        val infos = testIdeaViewModel.ideaList

        assertEquals(infos[0].comment, "dp를 이용하면 좋지 않을까?")
        assertEquals(infos[0].url, null)
    }

    @Test
    fun getIdeaInfos_moreThanOne() {
        //given
        testIdeaViewModel.saveIdeaInfo(null, "dp를 이용하면 좋지 않을까?", 1111)
        testIdeaViewModel.saveIdeaInfo(null, "투 포인터 이용하자", 1111)
        testIdeaViewModel.saveIdeaInfo(null, "그래프를 이용하자", 1111)
        testIdeaViewModel.getIdeaInfos(1111)

        //when
        val infos = testIdeaViewModel.ideaLst

        assertEquals(infos[0].comment, "dp를 이용하면 좋지 않을까?")
        assertEquals(infos[1].comment, "투 포인터 이용하자")
        assertEquals(infos[2].comment, "그래프를 이용하자")

    }
}