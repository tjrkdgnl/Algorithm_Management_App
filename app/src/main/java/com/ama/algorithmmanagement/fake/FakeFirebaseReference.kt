package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.utils.DateUtils
import timber.log.Timber
import java.lang.NullPointerException

class FakeFirebaseReference(
    private val mApp: Application,
    private val mFakeFirebaseDataProvider: FakeFirebaseDataProvider,
    private val mSharedPrefUtils: BaseSharedPreference
) {

    private val mUserId = mSharedPrefUtils.getUserIdFromLocal()
    private val mTierType = mSharedPrefUtils.getTierType()
    private val mDate = DateUtils.createDate()


    fun setUserInfo(userId: String, userPw: String, fcmToken: String?) {
        val userList = mFakeFirebaseDataProvider.userSnapShot

        userList.add(UserInfo(userId, userPw, fcmToken))
    }

    fun getUserInfo(userId: String): UserInfo? {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        for (userInfo in userList) {
            if (userInfo.userId == userId) {
                return userInfo
            }
        }

        return null
    }

    fun checkUserInfo(userId: String, password: String): Boolean {
        val userList = mFakeFirebaseDataProvider.userSnapShot

        for (userInfo in userList) {
            if (userInfo.userId == userId && userInfo.userPw == password) {
                Timber.e("$userId 가 firebase에 존재합니다.")
                return true;
            }
        }
        Timber.e("$userId 가 firebase에 존재하지 않습니다.")
        return false
    }

    fun setDateInfo() {
        val dateInfo = DateInfo(mDate)

        if (mUserId == null) {
            return
        }

        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == mUserId) {
                dateInfos.dateList.add(dateInfo)
                return
            }
        }

        val dateInfos = DateInfos(1, mUserId, mutableListOf(dateInfo))
        mFakeFirebaseDataProvider.dateSnapShot.add(dateInfos)
    }

    fun getDateInfos(): DateInfos? {
        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == mUserId) {
                return dateInfos
            }
        }
        return null
    }


    fun setIdeaInfo(url: String?, comment: String?, problemId: Int) {
        val ideaInfo = IdeaInfo(url, comment, mDate)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(ideaInfo))

        var userCheck = false
        var problemCheck = false

        if (mUserId == null) {
            return
        }

        for (userIdeaInfo in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (userIdeaInfo.userId == mUserId) {
                userCheck = true

                for (ideainfos in userIdeaInfo.ideaInfosList) {
                    if (ideainfos.problemId == problemId) {
                        problemCheck = true
                        ideainfos.ideaList.add(ideaInfo)
                        ideaInfos.count.plus(1)
                        return
                    }
                }

                if (!problemCheck) {
                    userIdeaInfo.ideaInfosList.add(ideaInfos)
                }
            }
        }

        if (!userCheck) {
            val userIdeaInfo = IdeaObject(mUserId, mutableListOf(ideaInfos))
            mFakeFirebaseDataProvider.ideaSnapShot.add(userIdeaInfo)
        }
    }

    fun getIdeaInfos(problemId: Int): IdeaInfos? {
        for (userIdeaInfo in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (userIdeaInfo.userId == mUserId) {

                for (ideaInfos in userIdeaInfo.ideaInfosList) {
                    if (ideaInfos.problemId == problemId) {
                        return ideaInfos
                    }
                }

            }
        }
        return null
    }


    fun setComment(problemId: Int, comment: String): Result<Boolean> {
        if (mUserId == null) {
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "userId"
                    )
                )
            )
        }

        if (mTierType == null) {
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "tierType"
                    )
                )
            )
        }

        val commentId = "RandomNumber"

        val newCommentInfo =
            CommentInfo(commentId, mUserId, mTierType, comment, mDate, 0)

        for (commentObject in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentObject.problemId == problemId) {
                commentObject.commentList.add(newCommentInfo)

                return Result.success(true)

            }
        }

        val commentObject = CommentObject(1, problemId, mutableListOf(newCommentInfo))

        mFakeFirebaseDataProvider.commentSnapShot.add(commentObject)

        return Result.success(true)
    }

    fun getCommentObject(problemId: Int): CommentObject? {
        for (commentinfo in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentinfo.problemId == problemId) {
                return commentinfo
            }
        }

        return null
    }

    fun setChildComment(commentId: String?, comment: String): Result<Boolean> {
        if (mUserId == null)
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "userId"
                    )
                )
            )

        if (mTierType == null) {
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "tierType"
                    )
                )
            )
        }

        if (commentId == null) {
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "commentId"
                    )
                )
            )
        }

        val childCommentInfo = ChildCommentInfo(mUserId, mTierType, comment, mDate)


        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                childCommentObject.commentChildList.add(childCommentInfo)
                return Result.success(true)
            }
        }

        val childCommentObject = ChildCommentObject(1, commentId, mutableListOf(childCommentInfo))
        mFakeFirebaseDataProvider.childCommentSnapShot.add(childCommentObject)

        return Result.success(true)
    }


    fun getChildCommentObject(commentId: String?): ChildCommentObject? {
        if (commentId == null)
            return null

        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                return childCommentObject
            }
        }

        return null
    }

    fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Result<Boolean> {
        if (mUserId == null) {
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "userId"
                    )
                )
            )
        }

        val tipProblem = TipProblem(problem, isShow, tipComment, mDate)

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == mUserId) {
                tipProblemObject.problemList.add(tipProblem)
                return Result.success(true)
            }
        }

        val tipProblemObject = TippingProblemObject(1, mUserId, mutableListOf(tipProblem))
        mFakeFirebaseDataProvider.tipProblemSnapShot.add(tipProblemObject)

        return Result.success(true)
    }

    fun getTippingProblem(): TippingProblemObject? {
        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == mUserId) {
                return tipProblemObject
            }
        }
        return null
    }
}