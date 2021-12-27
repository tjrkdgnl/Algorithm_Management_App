package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FakeFirebaseIdeaInfo {

    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference
    lateinit var mUserId: String

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserId("Default_User")

        mUserId = fakeSharedPreference.getUserId()!!

        fakeFirebaseReference =
            FakeFirebaseReference(
                FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()),
                DateUtils.createDate()
            )
    }


    @Test
    fun getIdeaInfos_exist_userId_returnIdeaInfos() {
        //given
//        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 dp로 풀어야해", 1111)

        //when
        val ideaInfos = fakeFirebaseReference.getIdeaInfos(mUserId,1110)
        val ideaInfo = ideaInfos?.ideaList?.get(0)

        //then
        assertEquals(ideaInfos?.problemId, 1110)
        assertEquals(ideaInfos?.ideaList?.get(0)?.url, null)
        assertEquals(ideaInfos?.ideaList?.get(0)?.comment, "이건 dp로 풀어야해")
        assertEquals(ideaInfos?.ideaList?.get(0)?.date, DateUtils.createDate())

    }

    @Test
    fun getIdeaInfos_exist_sameProblemIdea_returnIdeaInfos() {
        // given
        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 dp로 풀어야해", 1111)
        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 완탐으로 풀어야해", 1111)

        //when
        val ideaInfos = fakeFirebaseReference.getIdeaInfos(mUserId,1111)

        //then
        assertEquals(ideaInfos?.ideaList?.get(0)?.comment, "이건 dp로 풀어야해")
        assertEquals(ideaInfos?.ideaList?.get(1)?.comment, "이건 완탐으로 풀어야해")

    }

    @Test
    fun getIdeaInfos_exist_differentProblemIdea_returnIdeaInfos() {
        //given
        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 dp로 풀어야해", 1234)
        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 완탐으로 풀어야해", 4321)

        //when
        val ideaInfos1 = fakeFirebaseReference.getIdeaInfos(mUserId,1234)
        val ideaInfos2 = fakeFirebaseReference.getIdeaInfos(mUserId,4321)

        //then
        assertEquals(ideaInfos1?.ideaList?.get(0)?.comment, "이건 dp로 풀어야해")
        assertEquals(ideaInfos1?.problemId, 1234)

        assertEquals(ideaInfos2?.ideaList?.get(0)?.comment, "이건 완탐으로 풀어야해")
        assertEquals(ideaInfos2?.problemId, 4321)
    }

    @Test
    fun getIdeaInfos_dontExist_returnNull() {
        //given
        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 dp로 풀어야해", 1234)
        fakeFirebaseReference.setIdeaInfo(mUserId,null, "이건 완탐으로 풀어야해", 4321)

        //when
        val ideaInfos = fakeFirebaseReference.getIdeaInfos(mUserId,12312312)

        //then
        assertNull(ideaInfos)
    }


}