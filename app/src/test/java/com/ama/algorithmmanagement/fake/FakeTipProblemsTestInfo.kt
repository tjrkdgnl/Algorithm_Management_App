package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.model.DisplayName
import com.ama.algorithmmanagement.model.Tag
import com.ama.algorithmmanagement.model.TaggedProblem
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FakeTipProblemsTestInfo {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference
    lateinit var mUserId: String
    var mTierType: Int =0

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserId("Default_User")
        fakeSharedPreference.setTierType(1)

        mUserId = fakeSharedPreference.getUserId()!!
        mTierType = fakeSharedPreference.getTierType()!!


        fakeFirebaseReference =
            FakeFirebaseReference(
                FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()),
                DateUtils.createDate()
            )
    }

    @Test
    fun getNotTippingProblem(){
        //when
        val notTippingProblemObj = fakeFirebaseReference.getNotTippingProblemObject(mUserId)

        assertEquals(notTippingProblemObj?.userId, "Default_User")
        assertEquals(notTippingProblemObj?.problemInfoList?.size, 10)
        assertEquals(notTippingProblemObj?.problemInfoList?.get(0)?.tipComment, null)
        assertEquals(notTippingProblemObj?.problemInfoList?.get(0)?.isShow, true)
        assertEquals(notTippingProblemObj?.problemInfoList?.get(0)?.date, DateUtils.createDate())

    }

    @Test
    fun getTippingProblem() {
        //given
        val taggedProblem =
            TaggedProblem(
                1000, "A+B", true, false, 151801,  1, 17, true,  2.333,
                mutableListOf(
                    Tag(
                        "arithmetic",
                        false,
                        121,
                        494,
                        mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                    )
                )
            )

        //when
//        fakeFirebaseReference.setTippingProblem(mUserId,taggedProblem, true, "dp를 사용하면 좋다")

        val tippingProblemObject = fakeFirebaseReference.getTippingProblemObject(mUserId)

        //then
        assertEquals(tippingProblemObject?.userId, "Default_User")
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.tipComment, "dp를 사용하면 좋다")
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.isShow, true)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.date, DateUtils.createDate())

        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.problemId, 1000)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.acceptedUserCount, 151801)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.averageTries, 2.333)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.isLevelLocked, true)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.isPartial, false)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.isSolvable, true)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.level, 1)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.tags?.size, 1)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.votedUserCount, 17)

    }

    @Test
    fun getTippingProblem_moreThanOne_returnTippingObject() {
        //given
        val taggedProblem1 =
            TaggedProblem(
                1111, "A+B", true, false, 151801,  1, 17, true,  2.333,
                mutableListOf(
                    Tag(
                        "arithmetic",
                        false,
                        121,
                        494,
                        mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                    )
                )
            )
        val taggedProblem2 =
            TaggedProblem(
                2222, "A+B", true, false, 151801,  1, 17, true,  2.333,
                mutableListOf(
                    Tag(
                        "arithmetic",
                        false,
                        121,
                        494,
                        mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                    )
                )
            )

        //when
        fakeFirebaseReference.setTippingProblem(mUserId,taggedProblem1, true, "dp를 사용하면 좋다")
        fakeFirebaseReference.setTippingProblem(mUserId,taggedProblem2, true, "binarySearch를 이욯하면 되겠다")

        val tippingProblemObject = fakeFirebaseReference.getTippingProblemObject(mUserId)

        //then
        assertEquals(tippingProblemObject?.userId, "skjh0818")
        assertEquals(tippingProblemObject?.problemInfoList?.size, 2)
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.tipComment, "dp를 사용하면 좋다")
        assertEquals(tippingProblemObject?.problemInfoList?.get(0)?.problem?.problemId, 1111)

        assertEquals(
            tippingProblemObject?.problemInfoList?.get(1)?.tipComment,
            "binarySearch를 이욯하면 되겠다"
        )
        assertEquals(tippingProblemObject?.problemInfoList?.get(1)?.problem?.problemId, 2222)
    }


    @Test
    fun getTippingProblem_dontExist_returnNull() {
        //when
        val tippingProblemObject = fakeFirebaseReference.getTippingProblemObject(mUserId)

        //then
        assertNull(tippingProblemObject)
    }
}