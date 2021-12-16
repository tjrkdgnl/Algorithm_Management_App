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

        mSharedPrefUtils.setUserIdToLocal(userId)
    }

    fun getUserInfo(): UserInfo? {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        for (userInfo in userList) {
            if (userInfo.userId == mUserId) {
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

    fun setDateInfo(): Result<Boolean> {
        val dateInfo = DateInfo(mDate)

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

        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == mUserId) {
                dateInfos.dateList.add(dateInfo)
                return Result.success(true)
            }
        }

        val dateInfos = DateInfoObject(1, mUserId, mutableListOf(dateInfo))
        mFakeFirebaseDataProvider.dateSnapShot.add(dateInfos)
        return Result.success(true)
    }

    fun getDateInfos(): DateInfoObject? {
        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == mUserId) {
                return dateInfos
            }
        }
        return null
    }


    fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Result<Boolean> {
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

        val ideaInfo = IdeaInfo(url, comment, mDate)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(ideaInfo))

        for (ideaObject in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (ideaObject.userId == mUserId) {

                for (ideainfos in ideaObject.ideaInfosList) {
                    if (ideainfos.problemId == problemId) {
                        ideainfos.ideaList.add(ideaInfo)
                        return Result.success(true)
                    }
                }

                ideaObject.ideaInfosList.add(ideaInfos)
                return Result.success(true)
            }
        }

        val ideaObject = IdeaObject(mUserId, mutableListOf(ideaInfos))
        mFakeFirebaseDataProvider.ideaSnapShot.add(ideaObject)

        return Result.success(true)
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

    fun setComment(problemId: Int, comment: String): Result<CommentInfo> {
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

                return Result.success(newCommentInfo)

            }
        }

        val commentObject = CommentObject(1, problemId, mutableListOf(newCommentInfo))

        mFakeFirebaseDataProvider.commentSnapShot.add(commentObject)

        return Result.success(newCommentInfo)
    }

    fun getCommentObject(problemId: Int): CommentObject? {
        for (commentinfo in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentinfo.problemId == problemId) {
                return commentinfo
            }
        }

        return null
    }

    fun setChildComment(commentId: String, comment: String): Result<ChildCommentInfo> {
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

        val childCommentInfo = ChildCommentInfo(mUserId, mTierType, comment, mDate)


        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                childCommentObject.commentChildList.add(childCommentInfo)
                return Result.success(childCommentInfo)
            }
        }

        val childCommentObject = ChildCommentObject(1, commentId, mutableListOf(childCommentInfo))
        mFakeFirebaseDataProvider.childCommentSnapShot.add(childCommentObject)

        return Result.success(childCommentInfo)
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
    ): Result<TipProblem> {
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
                return Result.success(tipProblem)
            }
        }

        val tipProblemObject = TippingProblemObject(1, mUserId, mutableListOf(tipProblem))
        mFakeFirebaseDataProvider.tipProblemSnapShot.add(tipProblemObject)

        return Result.success(tipProblem)
    }

    fun getTippingProblemObject(): TippingProblemObject? {
        if (mUserId == null) {
            return null
        }

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == mUserId) {
                return tipProblemObject
            }
        }
        return null
    }

    fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean?,
        comment: String?
    ): Result<Boolean> {
        if (mUserId == null)
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "userId"
                    )
                )
            )

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == mUserId) {

                for (tipProblem in tipProblemObject.problemList.withIndex()) {
                    if (tipProblem.value.problem.problemId == problemId) {
                        if (isShow != null) {
                            tipProblem.value.isShow = isShow
                        }
                        if (comment != null) {
                            tipProblem.value.tipComment = comment
                        }
                        return Result.success(true)
                    }
                }
            }
        }

        return Result.success(false)
    }

    fun deleteTippingProblem(problemId: Int): Result<Boolean> {
        if (mUserId == null)
            return Result.failure(
                NullPointerException(
                    mApp.getString(
                        R.string.objectIsNull,
                        "userId"
                    )
                )
            )

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == mUserId) {

                for (tipProblem in tipProblemObject.problemList.withIndex()) {
                    if (tipProblem.value.problem.problemId == problemId) {
                        tipProblemObject.problemList.removeAt(tipProblem.index)
                        return Result.success(true)
                    }
                }
            }
        }
        return Result.success(false)
    }
}