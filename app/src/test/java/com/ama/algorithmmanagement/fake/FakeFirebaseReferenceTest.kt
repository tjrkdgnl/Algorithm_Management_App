package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.Model.DisplayName
import com.ama.algorithmmanagement.Model.Tag
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FakeFirebaseReferenceTest {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference
    lateinit var mUserId: String
    var mTierType: Int =0

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserId("skjh0818")
        fakeSharedPreference.setTierType(1)

        mUserId = fakeSharedPreference.getUserId()!!
        mTierType = fakeSharedPreference.getTierType()!!


        fakeFirebaseReference =
            FakeFirebaseReference(
                FakeFirebaseDataProvider(),
                DateUtils.createDate()
            )
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
        fakeFirebaseReference.setTippingProblem(mUserId,taggedProblem, true, "dp를 사용하면 좋다")

        val tippingProblemObject = fakeFirebaseReference.getTippingProblemObject(mUserId)

        //then
        assertEquals(tippingProblemObject?.userId, "skjh0818")
        assertEquals(tippingProblemObject?.problemList?.get(0)?.tipComment, "dp를 사용하면 좋다")
        assertEquals(tippingProblemObject?.problemList?.get(0)?.isShow, true)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.date, DateUtils.createDate())

        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.problemId, 1000)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.acceptedUserCount, 151801)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.averageTries, 2.333)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.isLevelLocked, true)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.isPartial, false)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.isSolvable, true)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.level, 1)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.tags?.size, 1)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.votedUserCount, 17)

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
        assertEquals(tippingProblemObject?.problemList?.size, 2)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.tipComment, "dp를 사용하면 좋다")
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.problemId, 1111)

        assertEquals(
            tippingProblemObject?.problemList?.get(1)?.tipComment,
            "binarySearch를 이욯하면 되겠다"
        )
        assertEquals(tippingProblemObject?.problemList?.get(1)?.problem?.problemId, 2222)
    }


    @Test
    fun getTippingProblem_dontExist_returnNull() {
        //when
        val tippingProblemObject = fakeFirebaseReference.getTippingProblemObject(mUserId)

        //then
        assertNull(tippingProblemObject)
    }
}