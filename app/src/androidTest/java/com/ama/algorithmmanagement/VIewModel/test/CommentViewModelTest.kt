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
    fun saveComment() {
        testCommentViewModel.mComment.value = "hello"
        testCommentViewModel.mProblemId.value = 1111

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

    @Test
    fun saveComment_moreThanOne() {
        testCommentViewModel.mProblemId.value = 1111

        for (i in 0 until 10) {
            testCommentViewModel.mComment.value = "hello$i"

            val checkData = combineWith(
                testCommentViewModel.mProblemId,
                testCommentViewModel.mComment
            ) { id, comment -> id != null && comment != null }

            val result = checkData.getOrAwaitValue()

            if (result) {
                testCommentViewModel.saveComment()
            }
        }

        val lst = testCommentViewModel.commentInfoList

        assertEquals(lst.size, 10)
        assertEquals(lst[0].comment, "hello0")
        assertEquals(lst[1].comment, "hello1")
        assertEquals(lst[2].comment, "hello2")
        assertEquals(lst[3].comment, "hello3")
        assertEquals(lst[4].comment, "hello4")
    }

}