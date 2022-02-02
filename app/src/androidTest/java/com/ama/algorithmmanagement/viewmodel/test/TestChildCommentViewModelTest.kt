package com.ama.algorithmmanagement.viewmodel.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import com.ama.algorithmmanagement.utils.combineWith
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestChildCommentViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var testChildCommentViewModel: TestChildCommentViewModel

    @Before
    fun init() {
        val fakeSharedPreference = FakeSharedPreference()

        fakeSharedPreference.setUserId("skjh0818")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext()), DateUtils.getDate()
        )

        val fakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider(ApplicationProvider.getApplicationContext()))

        testChildCommentViewModel = TestChildCommentViewModel(
            FakeRepository(ApplicationProvider.getApplicationContext(),fakeFirebaseReference, fakeNetworkService,fakeSharedPreference)
        )
    }

    @Test
    fun saveChildComment() {
        testChildCommentViewModel.mComment.value = "hello"
        testChildCommentViewModel.mCommentId.value = "RandomNumber"

        val check = combineWith(
            testChildCommentViewModel.mComment,
            testChildCommentViewModel.mCommentId
        ) { id, comment -> id != null && comment != null }.getOrAwaitValue()

        if (check) {
            testChildCommentViewModel.saveChildComment()
        }

        testChildCommentViewModel.initChildCommentObject(testChildCommentViewModel.mCommentId.value!!)

        val obj = testChildCommentViewModel.childCommentInfoList[0]

        assertEquals(obj.comment, "hello")
        assertEquals(obj.userId, "skjh0818")
        assertEquals(obj.tierType, 1)
    }

    @Test
    fun saveChildComment_moreThanOne() {
        testChildCommentViewModel.mCommentId.value = "RandomNumber"
        testChildCommentViewModel.initChildCommentObject(testChildCommentViewModel.mCommentId.value!!)

        val lst = testChildCommentViewModel.childCommentInfoList

        for (i in 0 until 5) {
            testChildCommentViewModel.mComment.value = "hello$i"

            val check = combineWith(
                testChildCommentViewModel.mComment,
                testChildCommentViewModel.mCommentId
            ) { id, comment -> id != null && comment != null }.getOrAwaitValue()

            if (check) {
                testChildCommentViewModel.saveChildComment()
            }
        }

        assertEquals(lst.size, 5)
        assertEquals(lst[0].comment, "hello0")
        assertEquals(lst[1].comment, "hello1")
        assertEquals(lst[2].comment, "hello2")
        assertEquals(lst[3].comment, "hello3")
        assertEquals(lst[4].comment, "hello4")

    }

}