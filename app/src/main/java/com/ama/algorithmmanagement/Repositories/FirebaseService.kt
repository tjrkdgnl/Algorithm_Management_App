package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import timber.log.Timber

@ExperimentalCoroutinesApi
class FirebaseService(private val mApp: Application) : BaseFirebaseService {
    private val firebaseRef = Firebase.database.reference
    private val userTable = mApp.getString(R.string.userTable)

    override suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?): Boolean {
        val key = firebaseRef.child(userTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, userTable))
            firebaseRef.child(userTable).push()
        }

        return if (checkUserInfo(userId, userPw)) {
            val userInfo = UserInfo(userId, userPw, fcmToken)
            firebaseRef.child(userTable).push().setValue(userInfo)

            true
        } else {
            false
        }
    }

    override suspend fun getUserInfo(userId: String): UserInfo? {
        val key = firebaseRef.child(userTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, userTable))
            return null
        }

        val snapshot = firebaseRef.child(userTable).get().await()

        for (user in snapshot.children) {
            val userInfo = user.getValue(UserInfo::class.java)

            userInfo?.let {
                if (it.userId == userId) {
                    Timber.e(it.toString())
                    return it
                }
            }
        }

        Timber.d("${userTable}에 ${userId}와 일치하는 UserInfo가 존재하지 않습니다.")
        return null
    }

    override suspend fun checkUserInfo(userId: String, password: String): Boolean {
        val snapshot = firebaseRef.child(userTable).get().await()

        for (user in snapshot.children) {
            val userInfo = user.getValue(UserInfo::class.java)

            userInfo?.let {
                if (it.userId == userId) {
                    return false
                }
            }
        }

        return true
    }

    override fun setDateInfo(userId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDateInfos(userId: String?): DateInfoObject? {
        TODO("Not yet implemented")
    }

    override fun setIdeaInfo(
        userId: String,
        url: String?,
        comment: String?,
        problemId: Int
    ): IdeaInfo {
        TODO("Not yet implemented")
    }

    override fun getIdeaInfos(userId: String?, problemId: Int): IdeaInfos? {
        TODO("Not yet implemented")
    }

    override fun setComment(
        userId: String,
        tierType: Int,
        problemId: Int,
        comment: String
    ): CommentInfo {
        TODO("Not yet implemented")
    }

    override fun getCommentObject(problemId: Int): CommentObject? {
        TODO("Not yet implemented")
    }

    override fun setChildComment(
        userId: String,
        tierType: Int,
        commentId: String,
        comment: String
    ): ChildCommentInfo {
        TODO("Not yet implemented")
    }

    override fun getChildCommentObject(commentId: String?): ChildCommentObject? {
        TODO("Not yet implemented")
    }

    override fun setTippingProblem(
        userId: String,
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem {
        TODO("Not yet implemented")
    }

    override fun getTippingProblemObject(userId: String): TippingProblemObject? {
        TODO("Not yet implemented")
    }

    override fun getNotTippingProblemObject(userId: String): TippingProblemObject? {
        TODO("Not yet implemented")
    }

    override fun modifyTippingProblem(
        userId: String,
        problemId: Int,
        isShow: Boolean?,
        comment: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTippingProblem(userId: String?, problemId: Int): Boolean {
        TODO("Not yet implemented")
    }


}