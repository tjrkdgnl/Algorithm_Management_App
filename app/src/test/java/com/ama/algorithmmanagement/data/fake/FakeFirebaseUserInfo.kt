package com.ama.algorithmmanagement.data.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FakeFirebaseUserInfo {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference
    lateinit var mUserId: String
    var mTierType: Int =0

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference()
        fakeSharedPreference.setUserId("skjh0818")
        fakeSharedPreference.setTierType(1)

        mUserId = fakeSharedPreference.getUserId()!!
        mTierType = fakeSharedPreference.getTierType()!!

        fakeFirebaseReference =
            FakeFirebaseReference(
                FakeFirebaseDataProvider(ApplicationProvider.getApplicationContext())
            )
    }

    @Test
    fun getUserInfo_exist_returnUserInfo() = runBlockingTest {
        //given
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)


        //when
        val userInfo = fakeFirebaseReference.getUserInfo(mUserId)

        //then
        assertEquals(userInfo?.userId, "skjh0818")
        assertEquals(userInfo?.userPw, "myPassword")
        assertEquals(userInfo?.fcmToken, null)
    }

    @Test
    fun getUserInfo_empty_returnNull()= runBlockingTest {
        //when
        val userInfo = fakeFirebaseReference.getUserInfo(mUserId)

        //then
        assertNull(userInfo)
    }

    @Test
    fun checkUserInfo_exist_returnTrue()= runBlockingTest {
        //given
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)

        //when
        val checkUser = fakeFirebaseReference.signUpUserInfo("skjh0818", "myPassword")

        //then
        assertTrue(checkUser)
    }

    @Test
    fun checkUserInfo_dontExist_returnFalse()= runBlockingTest {
        //when
        val checkUser = fakeFirebaseReference.signUpUserInfo("skjh0818", "myPassword")

        //then
        assertFalse(checkUser)
    }


}