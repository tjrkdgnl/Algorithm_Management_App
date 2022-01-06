package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class FakeFirebaseReference(
    private val mFakeFirebaseDataProvider: FakeFirebaseDataProvider,
    private val mDate: String
) : BaseFirebaseService {

    override suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?): Boolean {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        return userList.add(UserInfo(userId, userPw, fcmToken))
    }

    override suspend fun checkUserInfo(userId: String, password: String): Boolean {
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

    override suspend fun getUserInfo(userId: String): UserInfo? {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        for (userInfo in userList) {
            if (userInfo.userId == userId) {
                return userInfo
            }
        }
        return null
    }

    override suspend fun setDateInfo(userId: String): Boolean {
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

    override suspend fun getDateInfos(userId: String?) = flow<DateInfoObject?> {
        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == userId) {
                emit(dateInfos)
            }
        }
        emit(null)
    }

    override suspend fun setIdeaInfo(
        userId: String,
        url: String?,
        comment: String?,
        problemId: Int
    ): Boolean {

        val ideaInfo = IdeaInfo(url, comment, mDate)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(ideaInfo))

        for (ideaObject in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (ideaObject.userId == userId) {

                for (ideainfos in ideaObject.ideaInfosList) {
                    if (ideainfos.problemId == problemId) {
                        ideainfos.ideaList.add(ideaInfo)
                        return true
                    }
                }

                ideaObject.ideaInfosList.add(ideaInfos)
                return true
            }
        }

        val ideaObject = IdeaObject(userId, 0, mutableListOf(ideaInfos))
        mFakeFirebaseDataProvider.ideaSnapShot.add(ideaObject)

        return true
    }

    override suspend fun getIdeaInfos(userId: String, problemId: Int) = flow<IdeaInfos?> {
        for (userIdeaInfo in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (userIdeaInfo.userId == userId) {

                for (ideaInfos in userIdeaInfo.ideaInfosList) {
                    if (ideaInfos.problemId == problemId) {
                        emit(ideaInfos)
                    }
                }
            }
        }
        emit(null)
    }

    override suspend fun setComment(
        userId: String,
        tierType: Int,
        problemId: Int,
        comment: String
    ): Boolean {
        val commentId = "RandomNumber"

        val newCommentInfo =
            CommentInfo(commentId, userId, tierType, comment, mDate, 0)

        for (commentObject in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentObject.problemId == problemId) {
                commentObject.commentList.add(newCommentInfo)

                return true

            }
        }

        val commentObject = CommentObject(1, problemId, mutableListOf(newCommentInfo))

        mFakeFirebaseDataProvider.commentSnapShot.add(commentObject)

        return true
    }

    override suspend fun getCommentObject(problemId: Int) = flow<CommentObject?> {
        for (commentObject in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentObject.problemId == problemId) {
                emit(commentObject)
            }
        }
        emit(null)
    }

    override fun setChildComment(
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


    override fun getChildCommentObject(commentId: String?): ChildCommentObject? {
        if (commentId == null)
            return null

        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                return childCommentObject
            }
        }

        return null
    }

    override fun setTippingProblem(
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

    override fun getTippingProblemObject(userId: String): TippingProblemObject? {
        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                val lst =
                    tipProblemObject.problemList.filter { it.tipComment != null }.toMutableList()

                tipProblemObject.problemList.clear()
                tipProblemObject.problemList.addAll(ArrayList(lst))

                return tipProblemObject
            }
        }
        return null
    }

    override fun getNotTippingProblemObject(userId: String): TippingProblemObject? {
        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                val lst =
                    tipProblemObject.problemList.filter { it.tipComment == null }.toMutableList()

                tipProblemObject.problemList.clear()
                tipProblemObject.problemList.addAll(ArrayList(lst))

                return tipProblemObject
            }
        }

        return null
    }

    override fun modifyTippingProblem(
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

    override fun deleteTippingProblem(userId: String?, problemId: Int): Boolean {
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