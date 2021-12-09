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
        fakeSharedPreference = FakeSharedPreference()
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
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)

        val userInfo = fakeFirebaseReference.getUserInfo()

        assertEquals(userInfo?.userId, "skjh0818")
        assertEquals(userInfo?.userPw, "myPassword")
        assertEquals(userInfo?.fcmToken, null)
    }

    @Test
    fun getUserInfo_empty_returnNull() {
        val userInfo = fakeFirebaseReference.getUserInfo()

        assertNull(userInfo)
    }

    @Test
    fun checkUserInfo_exist_returnTrue() {
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)
        val checkUser = fakeFirebaseReference.checkUserInfo("skjh0818", "myPassword")

        assertTrue(checkUser)
    }

    @Test
    fun checkUserInfo_dontExist_returnFalse() {
        val checkUser = fakeFirebaseReference.checkUserInfo("skjh0818", "myPassword")

        assertFalse(checkUser)
    }


}