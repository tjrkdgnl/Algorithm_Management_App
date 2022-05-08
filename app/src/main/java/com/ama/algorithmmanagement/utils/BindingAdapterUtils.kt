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
     * @param recyclerView 댓글에 대한 리사이클러뷰 - 리니어
     * @param commentList 댓글 리스트
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
     * @param recyclerView 대댓글에 대한 리사이클러뷰 - 리니어
     * @param childCommentList 대댓글 리스트
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
     * @param pieChart 원형그래프
     * @param setSolvedProblemTierPieChart 해당유저가 티어별로 푼 문제에 대한 정보
     * @param position : 티어 0이면 브론즈 1이면 실버  2면 골드.....
     * 나의 실력그래프 티어별 통계
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
            // 티어가 0~31 까지 있고 5단위로 티어가 바뀌기떄문에 start 와 end 값 세팅
            // 0 은 언랭 티어기떄문에 브론즈 티어는 1부터 시작
            val start = (0 + pos) * 5 + 1
            val end = start + 4
            var solvedCount = 0
            setSolvedProblemTierPieChart?.let {
                for (i in start..end) {
                    // 해당 티어에 해결한 문제가 있을때만 그림
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
            // 티어에 맞는 색상 지정
            val dataset =
                PieDataSet(entries, "") // legend label 표시 안함
            dataset.valueFormatter =
                MpChartUtils.PieChartValueFormatter() // value Formatter 13.0 과 같은 소수를 "n 문제 해결" 포맷으로 변경
            dataset.colors =
                ColorUtils.getTierList(ColorUtils.tierConvertInt(pos)) // 티어에 맞는 색상 리스트 적용
            val data = PieData(dataset)
            data.setValueTextSize(11f)
            pieChart.data = data
        }
        // 파이차트 애니메이션
        pieChart.animateX(1000)
        pieChart.animateY(1000)

        pieChart.description.isEnabled = false // description 지우기
        pieChart.setEntryLabelTextSize(10f) // 레이블 (브론즈1,브론즈2...) 텍스트 크기
        pieChart.setEntryLabelColor(R.color.black) // 레이블 (브론즈1,브론즈2,브론즈3 ...) 텍스트 색상
        pieChart.setTouchEnabled(false) // 터치막기
        pieChart.isRotationEnabled = false // 차트 돌아가게 할지 여부

        // 차트 다시 그림 (데이터가 바뀌었을때 보려는 티어변경 파이차트를 다시 그려 최신데이터로 적용)
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
            // 티어가 0~31 까지 있고 5단위로 티어가 바뀌기떄문에 start 와 end 값 세팅
            // 0 은 언랭 티어기떄문에 브론즈 티어는 1부터 시작
            val start = (0 + pos) * 5 + 1
            val end = start + 4
            var solvedCount = 0
            handleNotSolvedProblem?.let {
                for (i in start..end) {
                    // 해당 티어에 해결한 문제가 있을때만 그림
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
     * @param barChart horizontalBarChart 수직 막대그래프
     * @param problems 유저가 해결한 문제에대한 정보가 담긴 객체
     * 나의 실력그래프 유형별 해결한 문제 통계
     */
    @JvmStatic
    @BindingAdapter("setSolvedProblemTypeBarChart")
    fun setSolvedProblemTypeBarChart(recyclerView: RecyclerView, problems: Problems?) {
        problems?.problemList?.let {
            // 메인화면에 보여줄 그래프는 10개로 세팅
            val adapter = recyclerView.adapter as? SolvedProblemStatsAdapter
            val problemHashMap = MpChartUtils.getTypeStatsSize(it,10,false)
            Timber.e("hashmap $problemHashMap")
            problemHashMap.toList().map { item->
                adapter?.addItem(item)
            }

        }
    }

    /**
     * @param recyclerView 리사이클러뷰 그리드 매니져
     * @param dates 해당 유저의 월별 통계가 저장되있는 객체
     * @param currentDate 현재 보여줘야될 날짜(년,월) 데이터
     */
    @JvmStatic
    @BindingAdapter("setDateInfoGridList", "currentDate")
    fun setDateInfoGridList(recyclerView: RecyclerView, dates: DateObject?, currentDate: String?) {
        val adapter = recyclerView.adapter as? KUserDateInfoAdapter
        // 어댑터 초기화 (상태값이 바겼을때를 위해 다시그리기위한 과정)
        adapter?.initialize()

        // 현재 월에서 월별 통계정보가 있는지 확인
        val year = dates?.yearInfo?.find { it ->
            it.year == DateUtils.getYear()
        }
        year?.let { yearInfo ->
            // 년 정보가 일치할시 월정보도 일치하는지 한번더 확인
            val month = yearInfo.monthInfoList.find { it ->
                // Calendar 에서 월은 0부터 시작하기때문에 비교할때 1 더해서 비교
                it.month == DateUtils.getStatDate().get(Calendar.MONTH) + 1
            }
            // 날짜(일) : count 로 해당월에 얼마나 활동했는지 저장할 해시맵
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
     * @param data 오늘 팁을 작성하지 않은 문제 데이터
     * 팁을 작성하지 않은 문제들 리사이클러뷰와 연동
     */
    @JvmStatic
    @BindingAdapter("setNoTipProblemViewPager")
    fun setNoTipProblemViewPager(viewpager: ViewPager2, data: MutableList<TipProblemInfo>?) {
        // 파베에서 notip 데이터를 요청완료해야 @param data 가 null 이 아니게되서
        // @param data 에 값이 있을때만 adapter 를 세팅해줌
        Timber.e("data : $data")
        data?.let {
            Timber.e("render : $data")
            val adapter = viewpager.adapter as? TipProblemViewPagerFragmentAdapter
            // 첫번째 문제 프레그먼트만 추가하고 나머지 문제는 건너뛰기 또는 작성완료를 했을때마다 프레그먼트 생성
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
            // 메인화면에 보여줄 그래프는 10개로 세팅
            val adapter = recyclerView.adapter as? SolvedProblemStatsAdapter
            val problemHashMap = MpChartUtils.getTypeStatsSize(it, isAll = true)
            Timber.e("hashmap $problemHashMap")
            problemHashMap.toList().map { item->
                adapter?.addItem(item)
            }

        }


    }
}