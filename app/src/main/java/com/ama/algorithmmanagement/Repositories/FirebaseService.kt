package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.utils.DateUtils
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import timber.log.Timber

@ExperimentalCoroutinesApi
class FirebaseService(private val mApp: Application) : BaseFirebaseService {
    private val mFirebaseRef = Firebase.database.reference
    private val mUserTable = mApp.getString(R.string.userTable)
    private val mIdeaTable = mApp.getString(R.string.ideaTable)
    private val mDate = DateUtils.createDate()

    override suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?): Boolean {
        val key = mFirebaseRef.child(mUserTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mUserTable))
            mFirebaseRef.child(mUserTable).push()
        }

        return if (checkUserInfo(userId, userPw)) {
            val userInfo = UserInfo(userId, userPw, fcmToken)
            mFirebaseRef.child(mUserTable).push().setValue(userInfo)

            true
        } else {
            false
        }
    }

    override suspend fun getUserInfo(userId: String): UserInfo? {
        val key = mFirebaseRef.child(mUserTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mUserTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mUserTable).get().await()

        for (user in snapshot.children) {
            val userInfo = user.getValue(UserInfo::class.java)

            userInfo?.let {
                if (it.userId == userId) {
                    Timber.e(it.toString())
                    return it
                }
            }
        }

        Timber.d("${mUserTable}에 ${userId}와 일치하는 UserInfo가 존재하지 않습니다.")
        return null
    }

    override suspend fun checkUserInfo(userId: String, password: String): Boolean {
        val snapshot = mFirebaseRef.child(mUserTable).get().await()

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

    override suspend fun setIdeaInfo(
        userId: String,
        url: String?,
        comment: String?,
        problemId: Int
    ): Boolean {
        val key = mFirebaseRef.child(mUserTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mUserTable))
            mFirebaseRef.child(mUserTable).push()
        }

        val snapshot = mFirebaseRef.child(mIdeaTable).get().await()

        val ideaInfo = IdeaInfo(url, comment, mDate)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(ideaInfo))

        for (idea in snapshot.children) {
            val ideaObject = idea.getValue(IdeaObject::class.java)

            ideaObject?.let {
                if (it.userId == userId) {
                    for (infos in it.ideaInfosList) {
                        if (infos.problemId == problemId) {
                            infos.ideaList.add(ideaInfo)
                            return true
                        }
                    }

                    it.ideaInfosList.add(ideaInfos)
                    return true
                }
            }
        }

        val ideaObject = IdeaObject(userId, mutableListOf(ideaInfos))
        mFirebaseRef.child(mUserTable).setValue(ideaObject)

        return true
    }

    override suspend fun getIdeaInfos(userId: String, problemId: Int): IdeaInfos? {
        val key = mFirebaseRef.child(mIdeaTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mIdeaTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mIdeaTable).get().await()

        for (idea in snapshot.children) {
            val ideaObject = idea.getValue(IdeaObject::class.java)

            ideaObject?.let {
                if (it.userId == userId) {
                    for (ideaInfos in it.ideaInfosList) {
                        if (ideaInfos.problemId == problemId) {
                            return ideaInfos
                        }
                    }
                }
            }
        }

        Timber.e(mApp.getString(R.string.firebaseIsNull, mIdeaTable))
        return null
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