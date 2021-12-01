package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.IdeaInfo
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import timber.log.Timber

class FakeFirebaseIdeaInfo {

    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserIdToLocal("skjh0818")

        fakeFirebaseReference =
            FakeFirebaseReference(FakeFirebaseDataProvider(), fakeSharedPreference)
    }


    @Test
    fun getIdeaInfos_exist_userId_returnIdeaInfos() {
        fakeFirebaseReference.setIdeaInfo(null, "이건 dp로 풀어야해", 1111)

        val ideaInfos = fakeFirebaseReference.getIdeaInfos(1111)
        val ideaInfo = ideaInfos?.ideaList?.get(0)

        assertEquals(ideaInfos?.problemId, 1111)
        assertEquals(ideaInfos?.ideaList?.get(0)?.url, null)
        assertEquals(ideaInfos?.ideaList?.get(0)?.comment, "이건 dp로 풀어야해")
        assertEquals(ideaInfos?.ideaList?.get(0)?.date, "2021.12.01")

    }

    @Test
    fun getIdeaInfos_exist_sameProblemIdea_returnIdeaInfos() {
        fakeFirebaseReference.setIdeaInfo(null, "이건 dp로 풀어야해", 1111)
        fakeFirebaseReference.setIdeaInfo(null, "이건 완탐으로 풀어야해", 1111)

        val ideaInfos = fakeFirebaseReference.getIdeaInfos(1111)

        assertEquals(ideaInfos?.ideaList?.get(0)?.comment, "이건 dp로 풀어야해")
        assertEquals(ideaInfos?.ideaList?.get(1)?.comment, "이건 완탐으로 풀어야해")

    }

    @Test
    fun getIdeaInfos_exist_differentProblemIdea_returnIdeaInfos() {
        fakeFirebaseReference.setIdeaInfo(null, "이건 dp로 풀어야해", 1234)
        fakeFirebaseReference.setIdeaInfo(null, "이건 완탐으로 풀어야해", 4321)

        val ideaInfos1 = fakeFirebaseReference.getIdeaInfos(1234)
        val ideaInfos2 = fakeFirebaseReference.getIdeaInfos(4321)

        assertEquals(ideaInfos1?.ideaList?.get(0)?.comment, "이건 dp로 풀어야해")
        assertEquals(ideaInfos1?.problemId, 1234)

        assertEquals(ideaInfos2?.ideaList?.get(0)?.comment, "이건 완탐으로 풀어야해")
        assertEquals(ideaInfos2?.problemId, 4321)
    }

    @Test
    fun getIdeaInfos_dontExist_returnNull() {
        fakeFirebaseReference.setIdeaInfo(null, "이건 dp로 풀어야해", 1234)
        fakeFirebaseReference.setIdeaInfo(null, "이건 완탐으로 풀어야해", 4321)

        val ideaInfos = fakeFirebaseReference.getIdeaInfos(12312312)

        assertNull(ideaInfos)
    }


}