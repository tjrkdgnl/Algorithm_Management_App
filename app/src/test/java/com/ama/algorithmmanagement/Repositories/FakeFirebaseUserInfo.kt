package com.ama.algorithmmanagement.Repositories

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FakeFirebaseUserInfo {
    lateinit var fakeFirebaseReference: FakeFirebaseReference

    @Before
    fun init() {
        fakeFirebaseReference = FakeFirebaseReference()
    }

    @Test
    fun getUserInfo_exist_returnUserInfo() {
        fakeFirebaseReference.setUserInfo("skjh0818", "myPassword", null)

        val userInfo = fakeFirebaseReference.getUserInfo("skjh0818")

        assertEquals(userInfo?.userId, "skjh0818")
        assertEquals(userInfo?.userPw, "myPassword")
        assertEquals(userInfo?.fcmToken, null)
    }

    @Test
    fun getUserInfo_empty_returnNull() {
        val userInfo = fakeFirebaseReference.getUserInfo("skjh0818")

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