package com.ama.algorithmmanagement.data.firebase

import android.app.Application
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.domain.base.BaseFirebaseService
import com.ama.algorithmmanagement.domain.entity.*
import com.ama.algorithmmanagement.utils.DateUtils
import com.ama.algorithmmanagement.utils.RandomIdGenerator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber


class FirebaseService(private val mApp: Application) : BaseFirebaseService {
    private val mFirebaseRef = Firebase.database.reference
    private val mUserTable = mApp.getString(R.string.userTable)
    private val mIdeaTable = mApp.getString(R.string.ideaTable)
    private val mDateTable = mApp.getString(R.string.dateTable)
    private val mCommentTable = mApp.getString(R.string.commentTable)
    private val mChildCommentTable = mApp.getString(R.string.childCommentTable)
    private val mTipTable = mApp.getString(R.string.tipTable)

    private val mDate = DateUtils.getDate()

    override suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?): Boolean {
        val tableKey = mFirebaseRef.child(mUserTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.generateTable, mUserTable))
            mFirebaseRef.child(mUserTable).push()
        }

        return if (signUpUserInfo(userId, userPw)) {
            val userInfo = UserInfo(userId, userPw, fcmToken)
            mFirebaseRef.child(mUserTable).push().setValue(userInfo)

            true
        } else {
            false
        }
    }

    override suspend fun getUserInfo(userId: String): UserInfo? {
        val tableKey = mFirebaseRef.child(mUserTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mUserTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mUserTable).get().await()

        for (obj in snapshot.children) {
            val userInfo = obj.getValue(UserInfo::class.java)

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

    override suspend fun signUpUserInfo(userId: String, password: String): Boolean {
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

    override suspend fun setDateInfo(userId: String, count: Int): Boolean {
        val tableKey = mFirebaseRef.child(mDateTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.generateTable, mDateTable))
            mFirebaseRef.child(mDateTable).push()
        }

        val snapshot = mFirebaseRef.child(mDateTable).get().await()

        val dateInfo = DateInfo(mDate, count)

        for (obj in snapshot.children) {
            val dateObject = obj.getValue(DateObject::class.java)

            dateObject?.let { dateObj ->
                if (dateObj.userId == userId) {

                    for (yearInfo in dateObj.yearInfo) {
                        if (DateUtils.getYear() == yearInfo.year) {

                            for (monthInfo in yearInfo.monthInfoList) {
                                if (monthInfo.dateList.contains(dateInfo)) {
                                    Timber.e(
                                        mApp.getString(
                                            R.string.existDataOnFirebase,
                                            mDateTable
                                        )
                                    )
                                    return false
                                }

                                if (DateUtils.getMonth() == monthInfo.month) {
                                    monthInfo.count++
                                    monthInfo.dateList.add(dateInfo)
                                    mFirebaseRef.child(mDateTable).child(obj.key!!)
                                        .updateChildren(dateObj.toMap())
                                    return true
                                }
                            }
                        }
                    }
                }
            }
        }

        val yearInfo = YearInfo(
            DateUtils.getYear(),
            mutableListOf(MonthInfo(DateUtils.getMonth(), 1, mutableListOf(dateInfo)))
        )
        val dateObject = DateObject(userId, mutableListOf(yearInfo))
        mFirebaseRef.child(mDateTable).push().setValue(dateObject)

        return true
    }

    override suspend fun getDateObject(userId: String?): DateObject? {
        val tableKey = mFirebaseRef.child(mDateTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mDateTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mDateTable).get().await()

        for (obj in snapshot.children) {
            val dateObject = obj.getValue(DateObject::class.java)

            dateObject?.let { dateObj ->
                if (dateObj.userId == userId) {
                    return dateObj
                }
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
        val tableKey = mFirebaseRef.child(mIdeaTable).key

        if (tableKey == null) {
            Timber.d(mApp.getString(R.string.generateTable, mIdeaTable))
            mFirebaseRef.child(mIdeaTable).push()
        }

        val snapshot = mFirebaseRef.child(mIdeaTable).get().await()

        val ideaInfo = IdeaInfo(url, comment, mDate)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(ideaInfo))

        for (obj in snapshot.children) {
            val ideaObject = obj.getValue(IdeaObject::class.java)

            ideaObject?.let {
                if (it.userId == userId) {
                    for (infos in it.ideaInfosList) {
                        if (infos.problemId == problemId) {
                            infos.ideaList.add(ideaInfo)
                            infos.count++
                            mFirebaseRef.child(mIdeaTable).child(obj.key!!)
                                .updateChildren(it.toMap())
                            return true
                        }
                    }

                    it.ideaInfosList.add(ideaInfos)
                    it.count++
                    mFirebaseRef.child(mIdeaTable).child(obj.key!!).updateChildren(it.toMap())
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
            val tableKey = mFirebaseRef.child(mIdeaTable).key

            if (tableKey == null) {
                Timber.e(mApp.getString(R.string.objectIsNull, mIdeaTable))
                trySend(null)
            }

            val listener =
                mFirebaseRef.child(mIdeaTable).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (obj in snapshot.children) {
                            val ideaObject = obj.getValue(IdeaObject::class.java)

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

    override suspend fun setComment(
        userId: String,
        tierType: Int,
        problemId: Int,
        comment: String
    ): Boolean {
        val tableKey = mFirebaseRef.child(mCommentTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.generateTable, mCommentTable))
            mFirebaseRef.child(mCommentTable).push()
        }

        val commentId = RandomIdGenerator.generateRandDomId()

        val commentInfo = CommentInfo(problemId, commentId, userId, tierType, comment, mDate, 0)

        val snapShot = mFirebaseRef.child(mCommentTable).get().await()

        for (obj in snapShot.children) {
            val commentObject = obj.getValue(CommentObject::class.java)

            commentObject?.let {
                if (it.problemId == problemId) {
                    it.commentList.add(commentInfo)
                    it.count = it.commentList.size
                    mFirebaseRef.child(tableKey!!).child(obj.key!!).updateChildren(it.toMap())
                    return true
                }
            }
        }

        val commentObject = CommentObject(1, problemId, mutableListOf(commentInfo))
        mFirebaseRef.child(mCommentTable).push().setValue(commentObject)
        return true
    }

    override suspend fun getCommentObject(problemId: Int): CommentObject? {
        val tableKey = mFirebaseRef.child(mCommentTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mCommentTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mCommentTable).get().await()

        for (obj in snapshot.children) {
            val commentObject = obj.getValue(CommentObject::class.java)

            commentObject?.let {
                if (it.problemId == problemId) {
                    return it
                }
            }
        }

        return null
    }


    private suspend fun getChildCountOfCommentInfo(
        problemId: Int,
        commentId: String
    ): Triple<String, Int, Int>? {
        val tableKey = mFirebaseRef.child(mCommentTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mCommentTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mCommentTable).get().await()

        for (obj in snapshot.children) {
            val commentObject = obj.getValue(CommentObject::class.java)

            commentObject?.let {
                if (it.problemId == problemId) {

                    for (commentInfo in it.commentList.withIndex()) {
                        if (commentInfo.value.commentId == commentId) {
                            return Triple(
                                mFirebaseRef.child(tableKey).child(obj.key!!).path.toString(),
                                commentInfo.index,
                                commentInfo.value.commentChildCount
                            )
                        }
                    }
                }
            }
        }

        return null
    }

    private fun runTransaction(list: MutableList<Pair<String, Any>>) {
        val updateMap = HashMap<String, Any>()

        for (pair in list) {
            updateMap[pair.first] = pair.second
        }

        mFirebaseRef.updateChildren(updateMap) { error, _ ->
            if (error != null) {
                Timber.e(error.toException())
            } else {
                list.clear()
            }
        }
    }

    override suspend fun setChildComment(
        problemId: Int,
        userId: String,
        tierType: Int,
        commentId: String,
        comment: String
    ): Boolean {
        val tableKey = mFirebaseRef.child(mChildCommentTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.generateTable, mChildCommentTable))
            mFirebaseRef.child(mChildCommentTable).push()
        }

        val updateList = mutableListOf<Pair<String, Any>>()

        val childCommentInfo = ChildCommentInfo(userId, tierType, comment, mDate)

        val snapShot = mFirebaseRef.child(mChildCommentTable).get().await()

        val childCountInfo = getChildCountOfCommentInfo(problemId, commentId)

        val key = childCountInfo?.first
        val pos = childCountInfo?.second
        val currentCount = childCountInfo?.third

        for (obj in snapShot.children) {
            val childCommentObject = obj.getValue(ChildCommentObject::class.java)

            childCommentObject?.let { childComment ->
                if (childComment.commentId == commentId) {

                    if (childCountInfo != null) {
                        childComment.commentChildList.add(childCommentInfo)
                        childComment.count = childComment.commentChildList.size

                        updateList.add(
                            Pair(
                                mFirebaseRef.child(tableKey!!).child(obj.key!!).path.toString(),
                                childComment
                            )
                        )
                        updateList.add(
                            Pair(
                                "${key}/commentList/${pos}/commentChildCount",
                                currentCount!! + 1
                            )
                        )

                        runTransaction(updateList)
                    }
                    return true
                }
            }
        }

        return if (childCountInfo != null) {
            updateList.add(
                Pair(
                    mFirebaseRef.child(mChildCommentTable).push().path.toString(),
                    ChildCommentObject(1, commentId, mutableListOf(childCommentInfo))
                )
            )

            updateList.add(
                Pair(
                    "${key}/commentList/${pos}/commentChildCount",
                    currentCount!! + 1
                )
            )

            runTransaction(updateList)
            true

        } else {
            false
        }
    }


    override suspend fun getChildCommentObject(commentId: String?): ChildCommentObject? {
        val tableKey = mFirebaseRef.child(mChildCommentTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mChildCommentTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mChildCommentTable).get().await()

        for (obj in snapshot.children) {
            val childCommentObject = obj.getValue(ChildCommentObject::class.java)

            childCommentObject?.let {
                if (it.commentId == commentId) {
                    return it
                }
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
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.generateTable, mTipTable))
            return false
        }

        val tipProblemInfo = TipProblemInfo(problem, isShow, tipComment, mDate)

        val snapShot = mFirebaseRef.child(mTipTable).get().await()

        for (obj in snapShot.children) {
            val tippingProblemObject = obj.getValue(TippingProblemObject::class.java)

            tippingProblemObject?.let {
                if (it.userId == userId) {
                    it.problemInfoList.add(tipProblemInfo)
                    it.count = it.problemInfoList.size
                    mFirebaseRef.child(tableKey).child(obj.key!!).updateChildren(it.toMap())
                    return true
                }
            }
        }

        return false
    }

    override suspend fun initTipProblems(
        userId: String,
        problems: List<TaggedProblem>
    ): TippingProblemObject {
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.generateTable, mTipTable))
            mFirebaseRef.child(mTipTable).push()
        }

        val tipProblems = mutableListOf<TipProblemInfo>()

        for (problem in problems) {
            tipProblems.add(
                TipProblemInfo(
                    problem,
                    false,
                    null,
                    mDate
                )
            )
        }

        val snapShot = mFirebaseRef.child(mTipTable).get().await()

        for (obj in snapShot.children) {
            val tippingProblemObject = obj.getValue(TippingProblemObject::class.java)

            tippingProblemObject?.let {
                if (it.userId == userId) {

                    for (tipProblemInfo in tipProblems) {
                        if (it.problemInfoList.contains(tipProblemInfo)) continue

                        it.problemInfoList.add(tipProblemInfo)
                        it.count = it.problemInfoList.size
                        mFirebaseRef.child(tableKey!!).child(obj.key!!).updateChildren(it.toMap())
                    }

                    return it
                }
            }
        }

        val tippingProblemObject = TippingProblemObject(tipProblems.size, userId, tipProblems)
        mFirebaseRef.child(mTipTable).push().setValue(tippingProblemObject)
        return tippingProblemObject
    }

    override suspend fun getAllTipProblems(userId: String): TippingProblemObject? {
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mTipTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mTipTable).get().await()

        val tippingProblemObject = TippingProblemObject(0, userId, mutableListOf())

        for (obj in snapshot.children) {
            val userTipObj = obj.getValue(TippingProblemObject::class.java)

            userTipObj?.let { userTip ->
                if (userTip.userId == userId) {
                    tippingProblemObject.count = userTip.count
                    tippingProblemObject.problemInfoList.addAll(userTip.problemInfoList)
                    return tippingProblemObject
                }
            }
        }

        return null
    }

    override suspend fun getTippingProblemObject(userId: String): TippingProblemObject? {
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mTipTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mTipTable).get().await()

        val filteredProblems = TippingProblemObject(0, userId, mutableListOf())

        for (obj in snapshot.children) {
            val tippingProblemObject = obj.getValue(TippingProblemObject::class.java)

            tippingProblemObject?.let {
                if (it.userId == userId) {
                    for (tipProblemInfo in it.problemInfoList) {
                        if (tipProblemInfo.tipComment == null) continue

                        filteredProblems.problemInfoList.add(tipProblemInfo)
                    }

                    filteredProblems.count = filteredProblems.problemInfoList.size

                    return filteredProblems
                }
            }
        }

        return null
    }

    override suspend fun getNotTippingProblemObject(userId: String): TippingProblemObject? {
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mTipTable))
            return null
        }

        val snapshot = mFirebaseRef.child(mTipTable).get().await()

        val filteredProblems = TippingProblemObject(0, userId, mutableListOf())

        for (obj in snapshot.children) {
            val tippingProblemObject = obj.getValue(TippingProblemObject::class.java)

            tippingProblemObject?.let {
                if (it.userId == userId) {
                    for (tipProblemInfo in it.problemInfoList) {
                        if (tipProblemInfo.tipComment != null) continue

                        filteredProblems.problemInfoList.add(tipProblemInfo)
                    }

                    filteredProblems.count = filteredProblems.problemInfoList.size

                    return filteredProblems
                }
            }
        }

        return null
    }

    override suspend fun modifyTippingProblem(
        userId: String,
        problemId: Int,
        isShow: Boolean,
        comment: String?
    ): Boolean {
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mTipTable))
            return false
        }

        val snapshot = mFirebaseRef.child(mTipTable).get().await()

        for (obj in snapshot.children) {
            val tippingProblemObject = obj.getValue(TippingProblemObject::class.java)

            tippingProblemObject?.let {
                if (it.userId == userId) {
                    for (tipProblemInfo in it.problemInfoList) {
                        tipProblemInfo.problem?.let { problem ->
                            if (problem.problemId == problemId) {
                                tipProblemInfo.isShow = isShow
                                tipProblemInfo.tipComment = comment
                                mFirebaseRef.child(tableKey).child(obj.key!!)
                                    .updateChildren(it.toMap())
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    override suspend fun deleteTippingProblem(userId: String?, problemId: Int): Boolean {
        val tableKey = mFirebaseRef.child(mTipTable).key

        if (tableKey == null) {
            Timber.e(mApp.getString(R.string.objectIsNull, mTipTable))
            return false
        }

        val snapshot = mFirebaseRef.child(mTipTable).get().await()

        for (obj in snapshot.children) {
            val tippingProblemObject = obj.getValue(TippingProblemObject::class.java)

            tippingProblemObject?.let {
                if (it.userId == userId) {
                    for (tipProblemInfo in it.problemInfoList) {
                        if (tipProblemInfo.problem?.problemId == problemId) {
                            it.problemInfoList.remove(tipProblemInfo)
                            mFirebaseRef.child(tableKey).child(obj.key!!)
                                .updateChildren(it.toMap())
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

}