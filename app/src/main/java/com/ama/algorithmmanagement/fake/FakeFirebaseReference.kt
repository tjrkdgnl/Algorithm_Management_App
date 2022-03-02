package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.base.BaseFirebaseService
import com.ama.algorithmmanagement.model.*
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.NullPointerException

class FakeFirebaseReference(
    private val mFakeFirebaseDataProvider: FakeFirebaseDataProvider
) : BaseFirebaseService {

    override suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?): Boolean {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        return userList.add(UserInfo(userId, userPw, fcmToken))
    }

    override suspend fun signUpUserInfo(userId: String, password: String): Boolean {
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

    override suspend fun setDateInfo(userId: String,count:Int): Boolean {
        val dateInfo = DateInfo(DateUtils.getDate(),count)
        val year = DateUtils.getYear()
        val month = DateUtils.getMonth()

        for (dateInfoObject in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfoObject.userId == userId) {

                for(yearInfo in dateInfoObject.yearInfo){
                    if (year == yearInfo.year) {

                        for(monthInfo in yearInfo.monthInfoList){
                            if (month == monthInfo.month) {
                                monthInfo.count++
                                monthInfo.dateList.add(dateInfo)
                            }
                        }
                    }
                }
            }
        }

        val yearInfo = YearInfo(year, mutableListOf(MonthInfo(month, 1, mutableListOf(dateInfo))))
        val dateObject = DateObject(userId, mutableListOf(yearInfo))
        mFakeFirebaseDataProvider.dateSnapShot.add(dateObject)

        return true
    }

    override suspend fun getDateObject(userId: String?): DateObject? {
        for (dateObject in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateObject.userId == userId) {
                return dateObject
            }
        }
        return null
    }

    override suspend fun setIdeaInfo(
        userId: String,
        url: String?,
        comment: String?,
        problemId: Int
    ): Boolean {

        val ideaInfo = IdeaInfo(url, comment, DateUtils.getDate())
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
            CommentInfo(problemId,commentId, userId, tierType, comment, DateUtils.getDate(), 0)

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

    override suspend fun getCommentObject(problemId: Int): CommentObject? {
        for (commentObject in mFakeFirebaseDataProvider.commentSnapShot) {
            if (commentObject.problemId == problemId) {
                return commentObject
            }
        }
        return null
    }

    override suspend fun setChildComment(
        problemId: Int,
        userId: String,
        tierType: Int,
        commentId: String,
        comment: String
    ): Boolean {
        val childCommentInfo = ChildCommentInfo(userId, tierType, comment, DateUtils.getDate())

        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                childCommentObject.commentChildList.add(childCommentInfo)
                return true
            }
        }
        //todo problemId 추가하기
        val childCommentObject = ChildCommentObject(1, commentId, mutableListOf(childCommentInfo))
        mFakeFirebaseDataProvider.childCommentSnapShot.add(childCommentObject)

        return false
    }


    override suspend fun getChildCommentObject(commentId: String?): ChildCommentObject? {
        if (commentId == null)
            return null

        for (childCommentObject in mFakeFirebaseDataProvider.childCommentSnapShot) {
            if (childCommentObject.commentId == commentId) {
                return childCommentObject
            }
        }

        return null
    }

    override suspend fun setTippingProblem(
        userId: String,
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {

        val tipProblem = TipProblemInfo(
            problem,
            isShow,
            tipComment,
            DateUtils.getDate()
        )

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                tipProblemObject.problemInfoList.add(tipProblem)
                return true
            }
        }

        val tipProblemObject = TippingProblemObject(
            1,
            userId, mutableListOf(tipProblem)
        )
        mFakeFirebaseDataProvider.tipProblemSnapShot.add(tipProblemObject)

        return true
    }

    override suspend fun initTipProblems(userId: String, problems: List<TaggedProblem>): TippingProblemObject? {
        val tipProblems = mutableListOf<TipProblemInfo>()

        for (problem in problems) {
            tipProblems.add(
                TipProblemInfo(
                    problem,
                    false,
                    null,
                    DateUtils.getDate()
                )
            )
        }

        val tipProblemObject = TippingProblemObject(1, userId, tipProblems)
        mFakeFirebaseDataProvider.tipProblemSnapShot.add(tipProblemObject)

        return tipProblemObject
    }

    override suspend fun getTippingProblemObject(userId: String): TippingProblemObject {
        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                val lst =
                    tipProblemObject.problemInfoList.filter { it.tipComment != null }
                        .toMutableList()

                tipProblemObject.problemInfoList.clear()
                tipProblemObject.problemInfoList.addAll(ArrayList(lst))

                return tipProblemObject
            }
        }
        throw NullPointerException("")
    }

    override suspend fun getNotTippingProblemObject(userId: String): TippingProblemObject {
        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {
                val lst =
                    tipProblemObject.problemInfoList.filter { it.tipComment == null }
                        .toMutableList()

                tipProblemObject.problemInfoList.clear()
                tipProblemObject.problemInfoList.addAll(ArrayList(lst))

                return tipProblemObject
            }
        }

        throw NullPointerException("")
    }

    override suspend fun modifyTippingProblem(
        userId: String,
        problemId: Int,
        isShow: Boolean,
        comment: String?
    ): Boolean {

        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {

                for (tipProblem in tipProblemObject.problemInfoList.withIndex()) {
                    if (tipProblem.value.problem?.problemId == problemId) {
                        tipProblem.value.isShow = isShow
                        tipProblem.value.tipComment = comment
                        return true
                    }
                }
            }
        }

        return true
    }

    override suspend fun deleteTippingProblem(userId: String?, problemId: Int): Boolean {
        for (tipProblemObject in mFakeFirebaseDataProvider.tipProblemSnapShot) {
            if (tipProblemObject.userId == userId) {

                for (tipProblem in tipProblemObject.problemInfoList.withIndex()) {
                    if (tipProblem.value.problem?.problemId == problemId) {
                        tipProblemObject.problemInfoList.removeAt(tipProblem.index)
                        return true
                    }
                }
            }
        }
        return true
    }
}