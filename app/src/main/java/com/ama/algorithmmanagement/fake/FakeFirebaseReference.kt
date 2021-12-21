package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import timber.log.Timber

class FakeFirebaseReference(
    private val mFakeFirebaseDataProvider: FakeFirebaseDataProvider,
    private val mDate: String
) {

    fun setUserInfo(userId: String, userPw: String, fcmToken: String?) {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        userList.add(UserInfo(userId, userPw, fcmToken))
    }

    fun getUserInfo(userId: String?): UserInfo? {
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

    fun setDateInfo(userId: String): Boolean {
        val dateInfo = DateInfo(mDate)

        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == userId) {
                dateInfos.dateList.add(dateInfo)
                return true
            }
        }

        val dateInfos = DateInfoObject(1, userId, mutableListOf(dateInfo))
        mFakeFirebaseDataProvider.dateSnapShot.add(dateInfos)
        return true
    }

    fun getDateInfos(userId: String?): DateInfoObject? {
        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == userId) {
                return dateInfos
            }
        }
        return null
    }


    fun setIdeaInfo(
        userId: String,
        url: String?,
        comment: String?,
        problemId: Int
    ): IdeaInfo {

        val ideaInfo = IdeaInfo(url, comment, mDate)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(ideaInfo))

        for (ideaObject in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (ideaObject.userId == userId) {

                for (ideainfos in ideaObject.ideaInfosList) {
                    if (ideainfos.problemId == problemId) {
                        ideainfos.ideaList.add(ideaInfo)
                        return ideaInfo
                    }
                }

                ideaObject.ideaInfosList.add(ideaInfos)
                return ideaInfo
            }
        }

        val ideaObject = IdeaObject(userId, mutableListOf(ideaInfos))
        mFakeFirebaseDataProvider.ideaSnapShot.add(ideaObject)

        return ideaInfo
    }

    fun getIdeaInfos(userId: String?, problemId: Int): IdeaInfos? {
        for (userIdeaInfo in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (userIdeaInfo.userId == userId) {

                for (ideaInfos in userIdeaInfo.ideaInfosList) {
                    if (ideaInfos.problemId == problemId) {
                        return ideaInfos
                    }
                }
            }
        }
        return null
    }

    fun setComment(
        userId: String,
        tierType: Int,
        problemId: Int,
        comment: String
    ): CommentInfo {
        val commentId = "RandomNumber"

        val newCommentInfo =
            CommentInfo(commentId, userId, tierType, comment, mDate, 0)

        for (commentObject in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentObject.problemId == problemId) {
                commentObject.commentList.add(newCommentInfo)

                return newCommentInfo

            }
        }

        val commentObject = CommentObject(1, problemId, mutableListOf(newCommentInfo))

        mFakeFirebaseDataProvider.commentSnapShot.add(commentObject)

        return newCommentInfo
    }

    fun getCommentObject(problemId: Int): CommentObject? {
        for (commentinfo in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentinfo.problemId == problemId) {
                return commentinfo
            }
        }
        return null
    }

    fun setChildComment(
        userId: String,
        tierType: Int,
        commentId: String,
        comment: String
    ): ChildCommentInfo {
        val childCommentInfo = ChildCommentInfo(userId, tierType, comment, mDate)

        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                childCommentObject.commentChildList.add(childCommentInfo)
                return childCommentInfo
            }
        }
        val childCommentObject = ChildCommentObject(1, commentId, mutableListOf(childCommentInfo))
        mFakeFirebaseDataProvider.childCommentSnapShot.add(childCommentObject)

        return childCommentInfo
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
        userId: String,
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem {

        val tipProblem = TipProblem(problem, isShow, tipComment, mDate)

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                tipProblemObject.problemList.add(tipProblem)
                return tipProblem
            }
        }

        val tipProblemObject = TippingProblemObject(
            1,
            userId, mutableListOf(tipProblem)
        )
        mFakeFirebaseDataProvider.tipProblemSnapShot.add(tipProblemObject)

        return tipProblem
    }

    fun getTippingProblemObject(userId: String?): TippingProblemObject? {
        if (userId == null) {
            return null
        }

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                return tipProblemObject
            }
        }
        return null
    }

    fun modifyTippingProblem(
        userId: String,
        problemId: Int,
        isShow: Boolean?,
        comment: String?
    ): Boolean {

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {

                for (tipProblem in tipProblemObject.problemList.withIndex()) {
                    if (tipProblem.value.problem.problemId == problemId) {
                        if (isShow != null) {
                            tipProblem.value.isShow = isShow
                        }
                        if (comment != null) {
                            tipProblem.value.tipComment = comment
                        }
                        return true
                    }
                }
            }
        }

        return true
    }

    fun deleteTippingProblem(userId: String?, problemId: Int): Boolean {

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {

                for (tipProblem in tipProblemObject.problemList.withIndex()) {
                    if (tipProblem.value.problem.problemId == problemId) {
                        tipProblemObject.problemList.removeAt(tipProblem.index)
                        return true
                    }
                }
            }
        }
        return true
    }
}