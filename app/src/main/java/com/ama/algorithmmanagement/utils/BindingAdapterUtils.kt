package com.ama.algorithmmanagement.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.presentation.search.adapter.SearchProblemAdapter
import com.ama.algorithmmanagement.domain.entity.*
import com.ama.algorithmmanagement.presentation.main.adapter.KRetryProblemsAdapter
import com.ama.algorithmmanagement.presentation.main.adapter.KUserDateInfoAdapter
import com.ama.algorithmmanagement.presentation.main.adapter.SolvedProblemStatsAdapter
import com.ama.algorithmmanagement.presentation.mytip.adapter.MyTipProblemsAdapter
import com.ama.algorithmmanagement.presentation.newSolvedProblem.SolvedProblemViewPagerFragment
import com.ama.algorithmmanagement.presentation.newSolvedProblem.adapter.TipProblemViewPagerFragmentAdapter
import com.ama.algorithmmanagement.presentation.notip.adapter.NoTipProblemsAdapter
import com.ama.algorithmmanagement.presentation.retryProblems.adapter.RetryProblemsInfoAdapter
import com.ama.algorithmmanagement.presentation.tryfailed.adapter.TryFailedAdapter
import com.ama.algorithmmanagement.presentation.tryhistory.adapter.IdeaAdapter
import com.ama.algorithmmanagement.presentation.tryhistory.adapter.MyCommentAdapter
import com.ama.algorithmmanagement.presentation.vpdetail.adapter.KChildCommentsAdapter
import com.ama.algorithmmanagement.presentation.vpdetail.adapter.KCommentsAdapter
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import timber.log.Timber
import java.util.*

object BindingAdapterUtils {

    /**
     * @param recyclerView ????????? ?????? ?????????????????? - ?????????
     * @param commentList ?????? ?????????
     */
    @JvmStatic
    @BindingAdapter("setCommentList")
    fun setCommentList(recyclerView: RecyclerView, commentList: MutableList<CommentInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as KCommentsAdapter
        commentList?.let {
            recyclerViewAdapter.updateList(commentList)
        }
    }

    /**
     * @param recyclerView ???????????? ?????? ?????????????????? - ?????????
     * @param childCommentList ????????? ?????????
     */
    @JvmStatic
    @BindingAdapter("setChildCommentList")
    fun setChildCommentList(
        recyclerView: RecyclerView,
        childCommentList: MutableList<ChildCommentInfo>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as KChildCommentsAdapter
        childCommentList?.let {
            recyclerViewAdapter.updateList(childCommentList)
        }
    }

    @JvmStatic
    @BindingAdapter("setNoTipProblemsList")
    fun setNoTipProblemsList(
        recyclerView: RecyclerView,
        noTipProblems: MutableList<TipProblemInfo>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as NoTipProblemsAdapter
        noTipProblems?.let {
            recyclerViewAdapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setMyTipProblemsList")
    fun setMyTipProblemsList(
        recyclerView: RecyclerView,
        myTipProblems: MutableList<TipProblemInfo>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as MyTipProblemsAdapter
        myTipProblems?.let {
            recyclerViewAdapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setTryFailedProblemsList")
    fun setTryFailedProblemsList(
        recyclerView: RecyclerView,
        tryFailedList: MutableList<TaggedProblem>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as TryFailedAdapter
        tryFailedList?.let {
            recyclerViewAdapter.updateList(tryFailedList)
        }
    }

    @JvmStatic
    @BindingAdapter("setMyCommentList")
    fun setMyCommentList(recyclerView: RecyclerView, commentInfo: MutableList<CommentInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as MyCommentAdapter
        commentInfo?.let {
            recyclerViewAdapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setMyIdeaList")
    fun setMyIdeaList(recyclerView: RecyclerView, ideaInfo: MutableList<IdeaInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as IdeaAdapter
        ideaInfo?.let {
            recyclerViewAdapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setRetryProblemList")
    fun setRetryProblemList(
        recyclerView: RecyclerView,
        retryProblem: MutableList<TipProblemInfo>?
    ) {
        val adapter = recyclerView.adapter as? RetryProblemsInfoAdapter
        Timber.e("solved $retryProblem")
        retryProblem?.let { list ->
            adapter?.setData(list.toMutableList())
        }

    }


    /**
     * @param pieChart ???????????????
     * @param setSolvedProblemTierPieChart ??????????????? ???????????? ??? ????????? ?????? ??????
     * @param position : ?????? 0?????? ????????? 1?????? ??????  2??? ??????.....
     * ?????? ??????????????? ????????? ??????
     */
    @JvmStatic
    @BindingAdapter("setSolvedProblemTierPieChart", "position")
    fun setSolvedProblemTierPieChart(
        pieChart: PieChart,
        setSolvedProblemTierPieChart: MutableList<Stats>?,
        position: Int?
    ) {
        val entries = mutableListOf<PieEntry>()
        pieChart.visibility = View.VISIBLE
        position?.let { pos ->
            // ????????? 0~31 ?????? ?????? 5????????? ????????? ?????????????????? start ??? end ??? ??????
            // 0 ??? ?????? ?????????????????? ????????? ????????? 1?????? ??????
            val start = (0 + pos) * 5 + 1
            val end = start + 4
            var solvedCount = 0
            setSolvedProblemTierPieChart?.let {
                for (i in start..end) {
                    // ?????? ????????? ????????? ????????? ???????????? ??????
                    if (it[i].solved != 0) {
                        entries.add(
                            PieEntry(
                                it[i].solved.toFloat(),
                                ColorUtils.intConvertToTier(i)
                            )
                        )
                        solvedCount++
                    }
                }
                if (solvedCount == 0) {
                    pieChart.visibility = View.GONE
                }
            }
            // ????????? ?????? ?????? ??????
            val dataset =
                PieDataSet(entries, "") // legend label ?????? ??????
            dataset.valueFormatter =
                MpChartUtils.PieChartValueFormatter() // value Formatter 13.0 ??? ?????? ????????? "n ?????? ??????" ???????????? ??????
            dataset.colors =
                ColorUtils.getTierList(ColorUtils.tierConvertInt(pos)) // ????????? ?????? ?????? ????????? ??????
            val data = PieData(dataset)
            data.setValueTextSize(11f)
            pieChart.data = data
        }
        // ???????????? ???????????????
        pieChart.animateX(1000)
        pieChart.animateY(1000)

        pieChart.description.isEnabled = false // description ?????????
        pieChart.setEntryLabelTextSize(10f) // ????????? (?????????1,?????????2...) ????????? ??????
        pieChart.setEntryLabelColor(R.color.black) // ????????? (?????????1,?????????2,?????????3 ...) ????????? ??????
        pieChart.setTouchEnabled(false) // ????????????
        pieChart.isRotationEnabled = false // ?????? ???????????? ?????? ??????

        // ?????? ?????? ?????? (???????????? ??????????????? ????????? ???????????? ??????????????? ?????? ?????? ?????????????????? ??????)
        pieChart.notifyDataSetChanged()
        pieChart.invalidate()
    }

    @JvmStatic
    @BindingAdapter("handleNotSolvedProblem", "tierPosition")
    fun handleNotSolvedProblem(
        view: TextView,
        handleNotSolvedProblem: MutableList<Stats>?,
        position: Int?
    ) {
        view.visibility = View.GONE
        position?.let { pos ->
            // ????????? 0~31 ?????? ?????? 5????????? ????????? ?????????????????? start ??? end ??? ??????
            // 0 ??? ?????? ?????????????????? ????????? ????????? 1?????? ??????
            val start = (0 + pos) * 5 + 1
            val end = start + 4
            var solvedCount = 0
            handleNotSolvedProblem?.let {
                for (i in start..end) {
                    // ?????? ????????? ????????? ????????? ???????????? ??????
                    if (it[i].solved != 0) {
                        solvedCount++
                    }
                }
                if (solvedCount == 0) {
                    view.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * @param barChart horizontalBarChart ?????? ???????????????
     * @param problems ????????? ????????? ??????????????? ????????? ?????? ??????
     * ?????? ??????????????? ????????? ????????? ?????? ??????
     */
    @JvmStatic
    @BindingAdapter("setSolvedProblemTypeBarChart")
    fun setSolvedProblemTypeBarChart(recyclerView: RecyclerView, problems: Problems?) {
        problems?.problemList?.let {
            // ??????????????? ????????? ???????????? 10?????? ??????
            val adapter = recyclerView.adapter as? SolvedProblemStatsAdapter
            val problemHashMap = MpChartUtils.getTypeStatsSize(it,10,false)
            Timber.e("hashmap $problemHashMap")
            problemHashMap.toList().map { item->
                adapter?.addItem(item)
            }

        }
    }

    /**
     * @param recyclerView ?????????????????? ????????? ?????????
     * @param dates ?????? ????????? ?????? ????????? ??????????????? ??????
     * @param currentDate ?????? ??????????????? ??????(???,???) ?????????
     */
    @JvmStatic
    @BindingAdapter("setDateInfoGridList", "currentDate")
    fun setDateInfoGridList(recyclerView: RecyclerView, dates: DateObject?, currentDate: String?) {
        val adapter = recyclerView.adapter as? KUserDateInfoAdapter
        // ????????? ????????? (???????????? ??????????????? ?????? ????????????????????? ??????)
        adapter?.initialize()

        // ?????? ????????? ?????? ??????????????? ????????? ??????
        val year = dates?.yearInfo?.find { it ->
            it.year == DateUtils.getYear()
        }
        year?.let { yearInfo ->
            // ??? ????????? ???????????? ???????????? ??????????????? ????????? ??????
            val month = yearInfo.monthInfoList.find { it ->
                // Calendar ?????? ?????? 0?????? ????????????????????? ???????????? 1 ????????? ??????
                it.month == DateUtils.getStatDate().get(Calendar.MONTH) + 1
            }
            // ??????(???) : count ??? ???????????? ????????? ??????????????? ????????? ?????????
            val hashMap = HashMap<Int, Int>()
            month?.dateList?.map {
                val day = it.date.split(".")[2]
                hashMap.put(day.toInt(), it.count)
            }
            adapter?.setList(date = DateUtils.getStatDate(), hashMap)
        }
        if (dates == null) {
            adapter?.setList(date = DateUtils.getStatDate())

        }
        Timber.e(dates.toString())
    }

    /**
     * @param viewpager viewpager2
     * @param data ?????? ?????? ???????????? ?????? ?????? ?????????
     * ?????? ???????????? ?????? ????????? ????????????????????? ??????
     */
    @JvmStatic
    @BindingAdapter("setNoTipProblemViewPager")
    fun setNoTipProblemViewPager(viewpager: ViewPager2, data: MutableList<TipProblemInfo>?) {
        // ???????????? notip ???????????? ?????????????????? @param data ??? null ??? ???????????????
        // @param data ??? ?????? ???????????? adapter ??? ????????????
        Timber.e("data : $data")
        data?.let {
            Timber.e("render : $data")
            val adapter = viewpager.adapter as? TipProblemViewPagerFragmentAdapter
            // ????????? ?????? ?????????????????? ???????????? ????????? ????????? ???????????? ?????? ??????????????? ??????????????? ??????????????? ??????
            val fragment = SolvedProblemViewPagerFragment()
            adapter?.addFragment(fragment)
        }
    }

    @JvmStatic
    @BindingAdapter("loadRetryProblemInfo")
    fun loadRetryProblemInfo(recyclerView: RecyclerView, data: MutableList<TipProblemInfo>?) {
        val adapter = recyclerView.adapter as? RetryProblemsInfoAdapter
        Timber.e("data $data")
        data?.let {
            adapter?.clearData()
            adapter?.setData(it)
        }
    }

    @JvmStatic
    @BindingAdapter("loadSearchProblems")
    fun loadSearchProblems(recyclerView: RecyclerView, data: MutableList<Keyword>?) {
        Timber.e("data: $data")
        val adapter = recyclerView.adapter as? SearchProblemAdapter
        adapter?.cleanData()
        adapter?.setData(data)

    }

    @JvmStatic
    @BindingAdapter("bind_loadCategoryStatus")
    fun loadCategoryStatus(recyclerView: RecyclerView, problems: Problems?) {
        problems?.problemList?.let {
            // ??????????????? ????????? ???????????? 10?????? ??????
            val adapter = recyclerView.adapter as? SolvedProblemStatsAdapter
            val problemHashMap = MpChartUtils.getTypeStatsSize(it, isAll = true)
            Timber.e("hashmap $problemHashMap")
            problemHashMap.toList().map { item->
                adapter?.addItem(item)
            }

        }


    }
}