package com.ama.algorithmmanagement.fake

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FakeFirebaseUserInfo {
    lateinit var fakeFirebaseReference: FakeFirebaseReference
    lateinit var fakeSharedPreference: FakeSharedPreference

    @Before
    fun init() {
        fakeSharedPreference = FakeSharedPreference(ApplicationProvider.getApplicationContext())
        fakeSharedPreference.setUserIdToLocal("skjh0818")

        fakeFirebaseReference =
            FakeFirebaseReference(
                ApplicationProvider.getApplicationContext(),
                FakeFirebaseDataProvider(),
                fakeSharedPreference
            )
    }

    @Test
    fun getUserInfo_exist_returnUserInfo() {
        //given
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)


        //when
        val userInfo = fakeFirebaseReference.getUserInfo()

        //then
        assertEquals(userInfo?.userId, "skjh0818")
        assertEquals(userInfo?.userPw, "myPassword")
        assertEquals(userInfo?.fcmToken, null)
    }

    @Test
    fun getUserInfo_empty_returnNull() {
        //when
        val userInfo = fakeFirebaseReference.getUserInfo()

        //then
        assertNull(userInfo)
    }

    @Test
    fun checkUserInfo_exist_returnTrue() {
        //given
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)

        //when
        val checkUser = fakeFirebaseReference.checkUserInfo("skjh0818", "myPassword")

        //then
        assertTrue(checkUser)
    }

    @Test
    fun checkUserInfo_dontExist_returnFalse() {
        //when
        val checkUser = fakeFirebaseReference.checkUserInfo("skjh0818", "myPassword")

        //then
        assertFalse(checkUser)
    }


}