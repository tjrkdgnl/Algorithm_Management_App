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
class FakeFirebaseChildComment {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference
    lateinit var mUserId: String
    var mTierType: Int = 0

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
    fun getChildCommentObject() = runBlockingTest {
        //given
        fakeFirebaseReference.setComment(mUserId, mTierType, 1111, "parent Comment")

        //intent로부터 commentId가 넘어옴
        //when
        val commentId = fakeFirebaseReference.getCommentObject(1111)?.let { commentObject ->
            commentObject.commentList[0].commentId
        }

        //when
        fakeFirebaseReference.setChildComment(mUserId, mTierType, commentId!!, "child Comment")
        val childCommentObject = fakeFirebaseReference.getChildCommentObject(commentId)

        //then
        childCommentObject?.let {
            assertEquals(it.commentChildList[0].userId, "skjh0818")
            assertEquals(it.commentId, commentId)
            assertEquals(it.commentChildList[0].comment, "child Comment")
            assertEquals(it.commentChildList[0].tierType, 1)
            assertEquals(it.commentChildList[0].date, DateUtils.getDate())

        }
    }

    @Test
    fun getChildCommentObject_parentIsNull_returnFailture()= runBlockingTest {
        //no given
        //when
        val commentId = fakeFirebaseReference.getCommentObject(1111)?.let { commentObject ->
            commentObject.commentList[0].commentId
        }

        //then
        fakeFirebaseReference.setChildComment(mUserId, mTierType, commentId!!, "child Comment")

    }

    @Test
    fun getChildCommentObject_moreThanChildComment_returnChildCommentList()= runBlockingTest {
        //given
        fakeFirebaseReference.setComment(mUserId, mTierType, 1111, "parent Comment")

        //when
        val commentId = fakeFirebaseReference.getCommentObject(1111)?.let { commentObject ->
            commentObject.commentList[0].commentId
        }

        //then
        fakeFirebaseReference.setChildComment(mUserId, mTierType, commentId!!, "child 1")
        fakeFirebaseReference.setChildComment(mUserId, mTierType, commentId, "child 2")
        fakeFirebaseReference.setChildComment(mUserId, mTierType, commentId, "child 3")

        //when
        val childCommentObject = fakeFirebaseReference.getChildCommentObject(commentId)

        //then
        assertEquals(childCommentObject?.commentId, "RandomNumber")
        assertEquals(childCommentObject?.commentChildList?.get(0)?.comment, "child 1")
        assertEquals(childCommentObject?.commentChildList?.get(1)?.comment, "child 2")
        assertEquals(childCommentObject?.commentChildList?.get(2)?.comment, "child 3")

    }
}