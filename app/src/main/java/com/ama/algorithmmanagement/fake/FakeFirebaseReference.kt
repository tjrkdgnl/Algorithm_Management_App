package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.UserInfo
import timber.log.Timber

class FakeFirebaseReference(private val fakeFirebaseDataProvider: FakeFirebaseDataProvider) {

    fun setUserInfo(userId: String, userPw: String, fcmToken: String?) {
        val userList = fakeFirebaseDataProvider.userSnapShot
        userList.add(UserInfo(userId, userPw, fcmToken))
    }

    fun getUserInfo(userId: String): UserInfo? {
        val userList = fakeFirebaseDataProvider.userSnapShot
        for (userInfo in userList) {
            if (userInfo.userId == userId) {
                return userInfo
            }
        }

        return null
    }

    fun checkUserInfo(userId: String, password: String): Boolean {
        val userList = fakeFirebaseDataProvider.userSnapShot

        for (userInfo in userList) {
            if (userInfo.userId == userId && userInfo.userPw == password) {
                Timber.e("$userId 가 firebase에 존재합니다.")
                return true;
            }
        }
        Timber.e("$userId 가 firebase에 존재하지 않습니다.")
        return false
    }

}