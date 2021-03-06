package com.ama.algorithmmanagement.data.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FakeFirebaseComment {
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
                FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext())
            )
    }


    @Test
    fun getCommentObject() = runBlockingTest {
        //given
//        fakeFirebaseReference.setComment(mUserId,mTierType,1111, "이거 어떻게 풀어요?")

        //when
        val commentObject = fakeFirebaseReference.getCommentObject(1111)
        val commentInfo = commentObject?.commentList?.get(0)

        //then
        assertEquals(commentInfo?.userId, "skjh0818")
        assertEquals(commentObject?.count, 1)
        assertEquals(commentObject?.problemId, 1110)
        assertEquals(commentInfo?.comment, "this is comment 0")
        assertEquals(commentInfo?.commentChildCount, 0)
        assertEquals(commentInfo?.commentId, "skjh0818RandomNumber")
        assertEquals(commentInfo?.tierType, 1)
        assertEquals(commentInfo?.date, DateUtils.getDate())
    }

    @Test
    fun getCommentInfo_moreThanOneValue()= runBlockingTest {
        //given
        fakeFirebaseReference.setComment(mUserId,mTierType,1111, "what?")
        fakeFirebaseReference.setComment(mUserId,mTierType,2222, "how?")

        //when
        val object1 = fakeFirebaseReference.getCommentObject(1111)
        val object2 = fakeFirebaseReference.getCommentObject(2222)

        //then
        assertEquals(object1?.problemId, 1111)
        assertEquals(object1?.commentList?.get(0)?.comment, "what?")
        assertEquals(object1?.commentList?.size, 1)

        assertEquals(object2?.problemId, 2222)
        assertEquals(object2?.commentList?.get(0)?.comment, "how?")
        assertEquals(object2?.commentList?.size, 1)
    }

    @Test
    fun getCommentInfo_moreThanComment()= runBlockingTest {
        //given
        fakeFirebaseReference.setComment(mUserId,mTierType,1111, "what?")
        fakeFirebaseReference.setComment(mUserId,mTierType,1111, "why?")

        //when
        val object1 = fakeFirebaseReference.getCommentObject(1111)

        //then
        assertEquals(object1?.problemId, 1111)
        assertEquals(object1?.commentList?.get(0)?.comment, "what?")
        assertEquals(object1?.commentList?.size, 2)

        assertEquals(object1?.commentList?.get(1)?.comment, "why?")

    }

}