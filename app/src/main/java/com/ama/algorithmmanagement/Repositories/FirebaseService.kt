package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.utils.DateUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber

@ExperimentalCoroutinesApi
class FirebaseService(private val mApp: Application) : BaseFirebaseService {
    private val mFirebaseRef = Firebase.database.reference
    private val mUserTable = mApp.getString(R.string.userTable)
    private val mIdeaTable = mApp.getString(R.string.ideaTable)
    private val mDateTable = mApp.getString(R.string.dateTable)
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

    override suspend fun setDateInfo(userId: String): Boolean {
        val key = mFirebaseRef.child(mDateTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mDateTable))
            mFirebaseRef.child(mDateTable).push()
        }

        val snapshot = mFirebaseRef.child(mDateTable).get().await()

        val dateInfo = DateInfo(mDate)

        for (obj in snapshot.children) {
            val dateObject = obj.getValue(DateInfoObject::class.java)

            dateObject?.let { dateObj ->
                if (dateObj.userId == userId) {
                    dateObj.count++
                    dateObj.dateList.add(dateInfo)
                    mFirebaseRef.child(mDateTable).child(obj.key!!).updateChildren(dateObj.toMap())

                    return true
                }
            }
        }

        val dateInfoObject = DateInfoObject(1, userId, mutableListOf(dateInfo))
        mFirebaseRef.child(mDateTable).push().setValue(dateInfoObject)

        return true
    }

    override suspend fun getDateInfos(userId: String?): Flow<DateInfoObject?> = callbackFlow {
        val key = mFirebaseRef.child(mDateTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mDateTable))
            trySend(null)
        }

        val listener =
            mFirebaseRef.child(mDateTable).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (obj in snapshot.children) {
                        val dateObject = obj.getValue(DateInfoObject::class.java)

                        dateObject?.let { dateObj ->
                            if (dateObj.userId == userId) {
                                trySend(dateObj)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Timber.e(error.message)
                }
            })

        awaitClose { mFirebaseRef.removeEventListener(listener) }
    }

    override suspend fun setIdeaInfo(
        userId: String,
        url: String?,
        comment: String?,
        problemId: Int
    ): Boolean {
        val key = mFirebaseRef.child(mIdeaTable).key

        if (key == null) {
            Timber.e(mApp.getString(R.string.firebaseIsNull, mIdeaTable))
            mFirebaseRef.child(mIdeaTable).push()
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
                            infos.count++
                            mFirebaseRef.child(mIdeaTable).child(idea.key!!)
                                .updateChildren(it.toMap())
                            return true
                        }
                    }

                    it.ideaInfosList.add(ideaInfos)
                    it.count++
                    mFirebaseRef.child(mIdeaTable).child(idea.key!!).updateChildren(it.toMap())
                    return true
                }
            }
        }

        val ideaObject = IdeaObject(userId, 1, arrayListOf(ideaInfos))
        mFirebaseRef.child(mIdeaTable).push().setValue(ideaObject)

        return true
    }

    override suspend fun getIdeaInfos(userId: String, problemId: Int): Flow<IdeaInfos?> =
        callbackFlow {
            val key = mFirebaseRef.child(mIdeaTable).key

            if (key == null) {
                Timber.e(mApp.getString(R.string.firebaseIsNull, mIdeaTable))
                trySend(null)
            }

            val listener =
                mFirebaseRef.child(mIdeaTable).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (idea in snapshot.children) {
                            val ideaObject = idea.getValue(IdeaObject::class.java)

                            ideaObject?.let {
                                if (it.userId == userId) {
                                    for (ideaInfos in it.ideaInfosList) {
                                        if (ideaInfos.problemId == problemId) {
                                            trySend(ideaInfos)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Timber.e(error.message)
                    }
                })

            awaitClose { mFirebaseRef.removeEventListener(listener) }
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