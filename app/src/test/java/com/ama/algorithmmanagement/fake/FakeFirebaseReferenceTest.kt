package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.Model.DisplayName
import com.ama.algorithmmanagement.Model.Tag
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class FakeFirebaseReferenceTest {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference


    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference(ApplicationProvider.getApplicationContext())
        fakeSharedPreference.setUserIdToLocal("skjh0818")
        fakeSharedPreference.setTierType(1)

        fakeFirebaseReference =
            FakeFirebaseReference(
                ApplicationProvider.getApplicationContext(),
                FakeFirebaseDataProvider(),
                fakeSharedPreference
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
        fakeFirebaseReference.setTippingProblem(taggedProblem, true, "dp를 사용하면 좋다")

        val tippingProblemObject = fakeFirebaseReference.getTippingProblem()

        //then
        assertEquals(tippingProblemObject?.userId, "skjh0818")
        assertEquals(tippingProblemObject?.problemList?.get(0)?.tipComment, "dp를 사용하면 좋다")
        assertEquals(tippingProblemObject?.problemList?.get(0)?.isShow, true)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.date, DateUtils.createDate())

        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.problemId, 1111)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.acceptedUserCount, 1)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.averageTries, 1.1)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.isLevelLocked, false)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.isPartial, false)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.isSolvable, true)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.level, 3)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.tags?.size, 0)
        assertEquals(tippingProblemObject?.problemList?.get(0)?.problem?.votedUserCount, 1000)

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
        fakeFirebaseReference.setTippingProblem(taggedProblem1, true, "dp를 사용하면 좋다")
        fakeFirebaseReference.setTippingProblem(taggedProblem2, true, "binarySearch를 이욯하면 되겠다")

        val tippingProblemObject = fakeFirebaseReference.getTippingProblem()

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
        val tippingProblemObject = fakeFirebaseReference.getTippingProblem()

        //then
        assertNull(tippingProblemObject)
    }
}