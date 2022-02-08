package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.model.*
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.utils.DateUtils

class FakeFirebaseDataProvider(private val mApp: Application) {
    private val mDefaultUserId = mApp.getString(R.string.default_user)
    private val mProblemId = mApp.getString(R.string.default_problemId).toInt()
    private val mCount = mApp.getString(R.string.default_count).toInt()

    val userSnapShot: MutableList<UserInfo> by lazy {
        mutableListOf()
    }

    val dateSnapShot: MutableList<DateObject> by lazy {
        MutableList(1) {
            val years = mutableListOf<YearInfo>( // 2년전부터 현재까지
                YearInfo(DateUtils.getYear() - 2, mutableListOf()),
                YearInfo(DateUtils.getYear() - 1, mutableListOf()),
                YearInfo(DateUtils.getYear(), mutableListOf())
            )

            for (year in years) {
                for (i in 0..11) { //1~12월
                    year.monthInfoList.add(MonthInfo(i, 0, mutableListOf()))
                }
            }
            DateObject(mDefaultUserId, years)
        }
    }

    val ideaSnapShot: MutableList<IdeaObject> by lazy {
        MutableList(mCount) {
            IdeaObject(mDefaultUserId, mCount, MutableList(mCount) {
                IdeaInfos(mCount, mProblemId + it, MutableList(mCount) {
                    IdeaInfo(null, "this is idea test ${it}", DateUtils.getDate())
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
                    DateUtils.getDate(),
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
                    DateUtils.getDate()
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
                TipProblemInfo(
                    problems.problemList?.get(it)!!,
                    false,
                    if (it < 15) "this is tip test $it" else null,
                    DateUtils.getDate()
                )
            })
        }
    }

}