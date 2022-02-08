package com.ama.algorithmmanagement.viewmodel.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import com.ama.algorithmmanagement.utils.combineWith
import com.ama.algorithmmanagement.utils.getOrAwaitValue
import org.junit.Assert.assertEquals
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

        fakeSharedPreference.setUserId("Default_User")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()), DateUtils.getDate()
        )

        val fakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider(ApplicationProvider.getApplicationContext()))

        testCommentViewModel = TestCommentViewModel(
            FakeRepository(
                ApplicationProvider.getApplicationContext(),
                fakeFirebaseReference,
                fakeNetworkService,
                fakeSharedPreference
            )
        )
    }

    @Test
    fun getComment(){
        testCommentViewModel.initCommentObject(1111)

        val lst = testCommentViewModel.mCommentObject?.commentList!!

        assertEquals(lst[0].userId,"Default_User")
        assertEquals(lst[0].commentId,"commentId0")
        assertEquals(lst[0].comment,"this is comment 0")

    }

    @Test
    fun saveComment() {
        testCommentViewModel.comment.value = "hello"
        testCommentViewModel.problemId.value = 1111

        val checkData = combineWith(
            testCommentViewModel.problemId,
            testCommentViewModel.comment
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
        testCommentViewModel.problemId.value = 1111

        for (i in 0 until 10) {
            testCommentViewModel.comment.value = "hello$i"

            val checkData = combineWith(
                testCommentViewModel.problemId,
                testCommentViewModel.comment
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