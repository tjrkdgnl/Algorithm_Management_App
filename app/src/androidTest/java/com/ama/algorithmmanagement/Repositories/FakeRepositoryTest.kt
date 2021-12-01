package com.ama.algorithmmanagement.Repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ama.algorithmmanagement.utils.getOrAwaitValue
import com.ama.algorithmmanagement.viewmodel.TestViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FakeRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var testViewModel: TestViewModel

    @Before
    fun init() {
        testViewModel = TestViewModel()
    }


    @Test
    fun setUserInfo() {
        testViewModel.setUserInfo()

        val userId = testViewModel.userId.getOrAwaitValue()
        val pwd = testViewModel.pwd.getOrAwaitValue()

        assertEquals(userId, "skjh0818")
        assertEquals(pwd, "myPwd")
    }

    @Test
    fun getuserInfo_exist_returnUserInfo() {
        testViewModel.setUserInfo()

        val userInfo = testViewModel.getUserInfo()

        assertEquals(userInfo?.userId,"skjh0818")
        assertEquals(userInfo?.userPw,"myPwd")
        assertEquals(userInfo?.fcmToken,null)

    }

    @Test
    fun getuserInfo_dontExist_returnNull() {
        val userInfo = testViewModel.getUserInfo()

        assertNull(userInfo)
    }

    @Test
    fun checkUserInfo_exist_returnTrue() {
        testViewModel.setUserInfo()

        assertTrue(testViewModel.checkUserInfo("skjh0818","myPwd"))
    }

    @Test
    fun checkUserInfo_dontExist_returnFalse() {

        assertFalse(testViewModel.checkUserInfo("skjh0818","myPwd"))
    }


}