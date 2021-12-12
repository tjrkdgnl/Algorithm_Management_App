package com.ama.algorithmmanagement.VIewModel.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.combineWith
import com.ama.algorithmmanagement.utils.getOrAwaitValue
import com.ama.algorithmmanagement.viewmodel.test.TestCommentViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class CommentViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var testCommentViewModel: TestCommentViewModel


    @Before
    fun init() {
        val fakeSharedPreference = FakeSharedPreference()

        fakeSharedPreference.setUserIdToLocal("skjh0818")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            ApplicationProvider.getApplicationContext(),
            FakeFirebaseDataProvider(), fakeSharedPreference
        )

        val fakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider())

        testCommentViewModel = TestCommentViewModel(
            FakeRepository(fakeFirebaseReference, fakeNetworkService)
        )
    }


    @Test
    fun initCommentObject() {
        testCommentViewModel.mComment.value = "hello"
        testCommentViewModel.setProblemId(1111)

        testCommentViewModel.saveComment()

        testCommentViewModel.initCommentObject(1111)

        val commentObject = testCommentViewModel.mCommentObject

        val commentInfo = commentObject?.commentList?.get(0)

        assertEquals(commentInfo?.commentId,"RandomNumber")
        assertEquals(commentInfo?.comment,"hello")
        assertEquals(commentInfo?.userId,"skjh0818")
        assertEquals(commentInfo?.tierType,1)
    }


    @Test
    fun saveComment() {
        testCommentViewModel.mComment.value = "hello"
        testCommentViewModel.setProblemId(1111)

        val checkData = combineWith(
            testCommentViewModel.mProblemId,
            testCommentViewModel.mComment
        ) { id, comment -> id != null && comment != null }

        val result = checkData.getOrAwaitValue()

        if (result) {
            testCommentViewModel.saveComment()

            val commentInfo = testCommentViewModel.commentInfoList[0]

            assertEquals(commentInfo.comment, "hello")
            assertEquals(commentInfo.userId, "skjh0818")
            assertEquals(commentInfo.tierType, 1)
            assertEquals(commentInfo.commentId, "RandomNumber")
        }
    }

}