package com.ama.algorithmmanagement.utils

import android.graphics.Color
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Adapter.*

import com.ama.algorithmmanagement.Adapter.test.*
import com.ama.algorithmmanagement.model.*
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import timber.log.Timber
import java.util.*
import kotlin.collections.HashMap

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("setItemList")
    fun setItemList(recyclerView: RecyclerView, list: MutableList<KProblemsOfClass>?) {
        val defaultRecyclerViewAdapter = recyclerView.adapter as KDefaultRecyclerViewAdapter
        defaultRecyclerViewAdapter.updateList(list)
    }


    @JvmStatic
    @BindingAdapter("setKeywords")
    fun setKeywordList(recyclerView: RecyclerView, list: MutableList<Keyword>?) {
        val keywordAdapter = recyclerView.adapter as TestSearchAdapter

        keywordAdapter.updateKeywords(list)
    }

    @JvmStatic
    @BindingAdapter("setTipProblemList")
    fun setSolvedProblemsList(
        recyclerView: RecyclerView,
        solvedAlgorithms: MutableList<TipProblemInfo>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as TestTipAdapter
        recyclerViewAdapter.updateList(solvedAlgorithms)
    }

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
    fun setChildCommentList(recyclerView: RecyclerView, childCommentList: MutableList<ChildCommentInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as KChildCommentsAdapter
        childCommentList?.let {
            recyclerViewAdapter.updateList(childCommentList)
        }
    }

    @JvmStatic
    @BindingAdapter("testIdeaProblems")
    fun setTestIdeaProblems(recyclerView: RecyclerView, problems: MutableList<IdeaInfo>?) {
        val adapter = recyclerView.adapter as TestIdeaAdpater

        problems?.let {
            adapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("testCommentList")
    fun setTestCommentList(recyclerView: RecyclerView, problems: MutableList<CommentInfo>?) {
        val adapter = recyclerView.adapter as TestCommentAdapter

        problems?.let {
            adapter.updateList(it)
        }
    }


    @JvmStatic
    @BindingAdapter("testChildCommentList")
    fun setTestChildCommentList(
        recyclerView: RecyclerView,
        problems: MutableList<ChildCommentInfo>?
    ) {
        val adapter = recyclerView.adapter as TestChildCommentAdapter

        problems?.let {
            adapter.updateList(it)
        }
    }


    @JvmStatic
    @BindingAdapter("testDateList")
    fun setTestDateList(recyclerView: RecyclerView, problems: MutableList<DateInfo>?) {
        val adapter = recyclerView.adapter as TestDateAdpater

        problems?.let {
            adapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setNoTipProblemsList")
    fun setNoTipProblemsList(recyclerView: RecyclerView, noTipProblems: MutableList<TipProblemInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as NoTipProblemsAdapter
        recyclerViewAdapter.updateList(noTipProblems)
    }

    @JvmStatic
    @BindingAdapter("setMyTipProblemsList")
    fun setMyTipProblemsList(recyclerView: RecyclerView, myTipProblems: MutableList<TipProblemInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as MyTipProblemsAdapter
        recyclerViewAdapter.updateList(myTipProblems)
    }

    @JvmStatic
    @BindingAdapter("setTryFailedProblemsList")
    fun setTryFailedProblemsList(recyclerView: RecyclerView, tryFailedList: MutableList<TaggedProblem>?) {
        val recyclerViewAdapter = recyclerView.adapter as TryFailedAdapter
        recyclerViewAdapter.updateList(tryFailedList)
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
        val adapter = recyclerView.adapter as? KRetryProblemsAdapter
        Timber.e("solved $retryProblem")
        adapter?.setRetryProblemItem(retryProblem)
        Timber.e("rep ${retryProblem.toString()}")
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
        pieChart.isRotationEnabled = false // 차트 돌아가게 할지 여부
        position?.let { pos ->
            // 티어가 0~31 까지 있고 5단위로 티어가 바뀌기떄문에 start 와 end 값 세팅
            // 0 은 언랭 티어기떄문에 브론즈 티어는 1부터 시작
            val start = (0 + pos) * 5 + 1
            val end = start + 5 - 1
            setSolvedProblemTierPieChart?.let {
                for (i in start..end) {
                    entries.add(PieEntry(it[i].solved.toFloat()))
                }
            }
            // 티어에 맞는 색상 지정
            val dataset =
                PieDataSet(entries, ColorUtils.getTierName(ColorUtils.tierConvertInt(pos)))
            dataset.colors = ColorUtils.getTierList(ColorUtils.tierConvertInt(pos))
            val data = PieData(dataset)
            pieChart.data = data
        }
        pieChart.animateX(1000)
        pieChart.animateY(1000)

        // 차트 다시 그림
        pieChart.notifyDataSetChanged()
        pieChart.invalidate()
    }

    /**
     * @param barChart horizontalBarChart 수직 막대그래프
     * @param problems 유저가 해결한 문제에대한 정보가 담긴 객체
     * 나의 실력그래프 유형별 해결한 문제 통계
     */
    @JvmStatic
    @BindingAdapter("setSolvedProblemTypeBarChart")
    fun setSolvedProblemTypeBarChart(barChart: HorizontalBarChart, problems: Problems?) {
        problems?.problemList?.let {
            // 메인화면에 보여줄 그래프는 10개로 세팅
            val solvedType = MpChartUtils.getTypeStatsSize(it, 10, false)
            Timber.e("solved Type :$solvedType")
            barChart.run {
                setDrawBarShadow(true) // 그래프 그림자
                setTouchEnabled(false) // 차트 터치 막기
                setDrawValueAboveBar(true) // 입력?값이 차트 위or아래에 그려질 건지 (true=위, false=아래)
                setPinchZoom(false) // 두손가락으로 줌 설정
                setDrawGridBackground(false) // 격자구조
                description.isEnabled = false // 그래프 오른쪽 하단에 라벨 표시
                legend.isEnabled = false // 차트 범례 설정(legend object chart)

                // horizontal chart bar  이기때문에 x 축과 y축이 반대임
                xAxis.run { // 아래 라벨 x축
                    isEnabled = true // 라벨 표시 설정 이속성은 valueFormatter 와 연관있는듯
                    valueFormatter =
                        MpChartUtils.LabelCustomFormatter(solvedType.keys.toList()) // 라벨 값 포멧 설정
                    position = XAxis.XAxisPosition.BOTTOM // 라벨 위치 설정
                    setDrawGridLines(false) // 격자구조
                    setDrawLimitLinesBehindData(true)
                    setDrawAxisLine(false)
                    setLabelCount(
                        10,
                        false
                    ) // mpchart 는 그리려는 그래프(Bar) 가 많아지면 특정 구간마다 레이블이 작성되는데 이속성을 통해 모든 그래프마다 레이블 보여주게 함
                    textSize = 12f // 라벨 크기
                    textColor = Color.BLACK
                }

                axisLeft.run { // 왼쪽 y축
                    isEnabled = false
                    axisMinimum = 0f // 최소값
                    axisMaximum = 100f // 최대값
                    granularity = 10f // 값 만큼 라인선 설정
                    setDrawLabels(false) // 값 셋팅 설정
                    textColor = Color.RED // 색상 설정
                    axisLineColor = Color.BLACK // 축 색상 설정
                    gridColor = Color.BLUE // 격자 색상 설정
                }
                axisRight.run { // 오른쪽 y축(왼쪽과동일)
                    isEnabled = false
                    textSize = 15f
                }

                animateY(1500) // y축 애니메이션
                animateX(1000) // x축 애니메이션
            }
            val values = mutableListOf<BarEntry>()
            val colorList = ColorTemplate.VORDIPLOM_COLORS

            solvedType.onEachIndexed { index, it ->
                values.add(BarEntry(index.toFloat(), it.value.toFloat()))
            }

            val set = BarDataSet(values, "유형별 통계")
                .apply {
                    setDrawIcons(false)
                    setDrawValues(true)
                    valueFormatter = MpChartUtils.ScoreCustomFormatter()
                    valueTextColor = Color.RED
                    colors = colorList.toTypedArray().toMutableList()
                }


            val dataSets = mutableListOf<IBarDataSet>()
            dataSets.add(set)

            val datas = BarData(dataSets)
                .apply {
                    setValueTextSize(10f)
                    barWidth = 0.5f
                }

            barChart.invalidate()
            barChart.data = datas
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
        Timber.e(dates.toString())
    }

    @JvmStatic
    @BindingAdapter("loadSearchProblems")
    fun loadSearchProblems(recyclerView: RecyclerView,data:MutableList<Keyword>?){
        Timber.e("data: $data")
        val adapter = recyclerView.adapter as? SearchProblemAdapter
        adapter?.cleanData()
        adapter?.setData(data)

    }
}