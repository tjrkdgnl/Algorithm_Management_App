package com.ama.algorithmmanagement.fake

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ama.algorithmmanagement.utils.DateUtils
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
                FakeFirebaseDataProvider(),
                DateUtils.createDate()
            )
    }

    @Test
    fun getUserInfo_exist_returnUserInfo() {
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
    fun getUserInfo_empty_returnNull() {
        //when
        val userInfo = fakeFirebaseReference.getUserInfo(mUserId)

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