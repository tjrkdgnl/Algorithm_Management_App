package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.TestLifecycleApplication
import java.lang.NullPointerException


@RunWith(AndroidJUnit4::class)
class FakeFirebaseComment {
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
    fun getCommentObject() {
        fakeFirebaseReference.setComment(1111, "이거 어떻게 풀어요?").onSuccess { isSuccess ->
            if (isSuccess) {

                val commentObject = fakeFirebaseReference.getCommentObject(1111)
                val commentInfo = commentObject?.commentList?.get(0)

                assertEquals(commentObject?.problemId, 1111)
                assertEquals(commentInfo?.comment, "이거 어떻게 풀어요?")
                assertEquals(commentInfo?.commentChildCount, 0)
                assertEquals(commentInfo?.commentId, "skjh0818RandomNumber")
                assertEquals(commentInfo?.userId, "skjh0818")
                assertEquals(commentInfo?.tierType, 1)
                assertEquals(commentInfo?.date, DateUtils.createDate())
            } else {
                assertEquals(isSuccess, true)
            }

        }.onFailure {
            assertThrows(it.message, NullPointerException::class.java) {

            }
        }
    }

    @Test
    fun getCommentInfo_moreThanOneValue() {
        fakeFirebaseReference.setComment(1111, "what?")
        fakeFirebaseReference.setComment(2222, "how?")

        val object1 = fakeFirebaseReference.getCommentObject(1111)
        val object2 = fakeFirebaseReference.getCommentObject(2222)

        assertEquals(object1?.problemId, 1111)
        assertEquals(object1?.commentList?.get(0)?.comment, "what?")
        assertEquals(object1?.commentList?.size, 1)

        assertEquals(object2?.problemId, 2222)
        assertEquals(object2?.commentList?.get(0)?.comment, "how?")
        assertEquals(object2?.commentList?.size, 1)
    }

    @Test
    fun getCommentInfo_moreThanComment() {
        fakeFirebaseReference.setComment(1111, "what?")
        fakeFirebaseReference.setComment(1111, "why?")

        val object1 = fakeFirebaseReference.getCommentObject(1111)

        assertEquals(object1?.problemId, 1111)
        assertEquals(object1?.commentList?.get(0)?.comment, "what?")
        assertEquals(object1?.commentList?.size, 2)

        assertEquals(object1?.commentList?.get(1)?.comment, "why?")

    }

}