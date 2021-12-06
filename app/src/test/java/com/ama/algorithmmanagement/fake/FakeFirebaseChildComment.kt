package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.IllegalStateException

@RunWith(AndroidJUnit4::class)
class FakeFirebaseChildComment {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
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
    fun getChildCommentObject() {
        fakeFirebaseReference.setComment(1111, "parent Comment")

        //intent로부터 commentId가 넘어옴
        val commentId = fakeFirebaseReference.getCommentObject(1111)?.let { commentObject ->
            commentObject.commentList[0].commentId
        }

        fakeFirebaseReference.setChildComment(commentId, "child Comment").onSuccess { isSuccess ->

            val childCommentObject = fakeFirebaseReference.getChildCommentObject(commentId)

            childCommentObject?.let {
                assertEquals(it.commentId, commentId)
                assertEquals(it.commentChildList[0].comment, "child Comment")
                assertEquals(it.commentChildList[0].userId, "skjh0818")
                assertEquals(it.commentChildList[0].tierType, 1)
                assertEquals(it.commentChildList[0].date, DateUtils.createDate())
            }
        }.onFailure {
            assertThrows(IllegalStateException::class.java) {

            }
        }
    }

    @Test
    fun getChildCommentObject_parentIsNull_returnFailture() {
//        fakeFirebaseReference.setComment(1111, "parent Comment")

        val commentId = fakeFirebaseReference.getCommentObject(1111)?.let { commentObject ->
            commentObject.commentList[0].commentId
        }

        fakeFirebaseReference.setChildComment(commentId, "child Comment").onSuccess { isSuccess ->

        }.onFailure {
            assertThrows(IllegalStateException::class.java) {

            }
        }
    }

    @Test
    fun getChildCommentObject_moreThanChildComment_returnChildCommentList() {
        fakeFirebaseReference.setComment(1111, "parent Comment")

        val commentId = fakeFirebaseReference.getCommentObject(1111)?.let { commentObject ->
            commentObject.commentList[0].commentId
        }

        fakeFirebaseReference.setChildComment(commentId, "child 1")
        fakeFirebaseReference.setChildComment(commentId, "child 2")
        fakeFirebaseReference.setChildComment(commentId, "child 3")

        val childCommentObject = fakeFirebaseReference.getChildCommentObject(commentId)

        assertEquals(childCommentObject?.commentId,"RandomNumber")
        assertEquals(childCommentObject?.commentChildList?.get(0)?.comment,"child 1")
        assertEquals(childCommentObject?.commentChildList?.get(1)?.comment,"child 2")
        assertEquals(childCommentObject?.commentChildList?.get(2)?.comment,"child 3")

    }
}