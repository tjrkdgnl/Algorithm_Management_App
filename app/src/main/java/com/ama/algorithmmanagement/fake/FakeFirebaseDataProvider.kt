package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.utils.DateUtils

class FakeFirebaseDataProvider(private val mApp: Application) {
    private val mDefaultUserId = mApp.getString(R.string.default_user)
    private val mProblemId = mApp.getString(R.string.default_problemId).toInt()
    private val mCount = mApp.getString(R.string.default_count).toInt()

    val userSnapShot: MutableList<UserInfo> by lazy {
        mutableListOf()
    }

    val dateSnapShot: MutableList<DateInfoObject> by lazy {
        MutableList(20) {
            DateInfoObject(mCount, mDefaultUserId, MutableList(20) {
                DateInfo(DateUtils.createDate())
            })
        }
    }

    val ideaSnapShot: MutableList<IdeaObject> by lazy {
        MutableList(mCount) {
            IdeaObject(mDefaultUserId,mCount, MutableList(mCount) {
                IdeaInfos(mCount, mProblemId + it, MutableList(mCount) {
                    IdeaInfo(null, "this is idea test ${it}", DateUtils.createDate())
                })
            })
        }
    }

    val commentSnapShot: MutableList<CommentObject> by lazy {
        MutableList(mCount) { it ->
            CommentObject(mCount, mProblemId + it, MutableList(mCount) {
                CommentInfo(
                    "commentId${it}",
                    mDefaultUserId,
                    it,
                    "this is comment $it",
                    DateUtils.createDate(),
                    10
                )
            })
        }
    }

    val childCommentSnapShot: MutableList<ChildCommentObject> by lazy {
        MutableList(mCount) { it ->
            ChildCommentObject(mCount, "commentId${it}", MutableList(mCount) {
                ChildCommentInfo(
                    "user_${it}",
                    it,
                    "this is child comment test $it",
                    DateUtils.createDate()
                )
            })
        }
    }

    val tipProblemSnapShot: MutableList<TippingProblemObject> by lazy {
        val problems = Problems(
            mCount, MutableList(mCount) {
                TaggedProblem(
                    it + mProblemId, "A+B", true, false, 151801, it + 1, 17, true, it + 2.333,
                    mutableListOf(
                        Tag(
                            "arithmetic",
                            false,
                            it + 121,
                            it + 494,
                            mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                        )
                    )
                )
            }
        )

        MutableList(mCount) {
            TippingProblemObject(mCount, mDefaultUserId, MutableList(mCount) {
                TipProblem(
                    problems.problemList?.get(it)!!,
                    false,
                    if (it < 15) "this is tip test $it" else null,
                    DateUtils.createDate()
                )
            })
        }
    }

}