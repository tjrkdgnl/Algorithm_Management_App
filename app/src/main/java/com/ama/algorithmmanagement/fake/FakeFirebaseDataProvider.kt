package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.utils.DateUtils

class FakeFirebaseDataProvider(private val mApp: Application) {
    private val mDefaultUserId = mApp.getString(R.string.default_user)

    val userSnapShot: MutableList<UserInfo> by lazy {
        mutableListOf()
    }

    val dateSnapShot: MutableList<DateInfoObject> by lazy {
        MutableList(20) {
            DateInfoObject(20, mDefaultUserId, MutableList(20) {
                DateInfo(DateUtils.createDate())
            })
        }
    }

    val ideaSnapShot: MutableList<IdeaObject> by lazy {
        MutableList(20) {
            IdeaObject(mDefaultUserId, MutableList(20) {
                IdeaInfos(20, 1110 + it, MutableList(20) {
                    IdeaInfo(null, "this is idea test ${it}", DateUtils.createDate())
                })
            })
        }
    }

    val commentSnapShot: MutableList<CommentObject> by lazy {
        MutableList(20) { it->
            CommentObject(20, 1110 + it, MutableList(20) {
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
        MutableList(20) {it->
            ChildCommentObject(20, "commentId${it}", MutableList(20) {
                ChildCommentInfo("user_${it}", it, "this is child comment test $it", DateUtils.createDate())
            })
        }
    }

    val tipProblemSnapShot: MutableList<TippingProblemObject> by lazy {
        val problems = Problems(
            20, MutableList(20) {
                TaggedProblem(
                    it + 1000, "A+B", true, false, 151801, it + 1, 17, true, it + 2.333,
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

        MutableList(20) {
            TippingProblemObject(20, mDefaultUserId, MutableList(20) {
                TipProblem(
                    problems.problemList?.get(it)!!,
                    false,
                    "this is tip test $it",
                    DateUtils.createDate()
                )
            })
        }
    }

}