package com.ama.algorithmmanagement.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Adapter.KRetryProblemsAdapter
import com.ama.algorithmmanagement.Adapter.KUserDateInfoAdapter
import com.ama.algorithmmanagement.Adapter.test.*
import com.ama.algorithmmanagement.model.*
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import timber.log.Timber

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("setItemList")
    fun setItemList(recyclerView: RecyclerView, list: MutableList<KProblemsOfClass>?) {
        val defaultRecyclerViewAdapter = recyclerView.adapter as KDefaultRecyclerViewAdapter
        defaultRecyclerViewAdapter.updateList(list)
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
    fun setTestChildCommentList(recyclerView: RecyclerView, problems: MutableList<ChildCommentInfo>?) {
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
    @BindingAdapter("setRetryProblemList")
    fun setRetryProblemList(
        recyclerView: RecyclerView,
        retryProblem: MutableList<TipProblemInfo>?
    ) {
        val adapter = recyclerView.adapter as? KRetryProblemsAdapter
        adapter?.setRetryProblemItem(retryProblem)
        Timber.e("rep ${retryProblem.toString()}")
    }


    @JvmStatic
    @BindingAdapter("setSolvedProblemTierPieChart", "position")
    fun setSolvedProblemTierPieChart(
        pieChart: PieChart,
        setSolvedProblemTierPieChart: MutableList<Stats>?,
        position: Int?
    ) {
        val entries = mutableListOf<PieEntry>()
        var sum = 0.0f
        val tempIcon =
            pieChart.context.getDrawable(com.ama.algorithmmanagement.R.drawable.bronze_5_icon) // TODO 추후 티어별로 이미지 가져와서 보여줄 예정
        val bitmap = (tempIcon as BitmapDrawable).bitmap
        val changeIcon = BitmapDrawable(
            pieChart.context.resources,
            Bitmap.createScaledBitmap(bitmap, 15, 15, true)
        )
        pieChart.isRotationEnabled = false // 차트 돌아가게 할지 여부
        position?.let { pos ->
            val start = (0 + pos) * 5
            val end = start + 5 - 1
            Timber.e("start $start , end $end")
            setSolvedProblemTierPieChart?.let {
                for (i in 0..4) {
                    sum += it[i].solved
                }
                for (i in start..end) {
                    Timber.e((it[i].solved / sum * 100).toString())
                    entries.add(PieEntry(it[i].solved.toFloat()))
                }
            }
            Timber.e(entries.toString())
            val dataset = PieDataSet(entries, TierColors.getTierName(pos))
            dataset.colors = TierColors.getTierList(pos)
            val data = PieData(dataset)
            pieChart.data = data
        }
        pieChart.animateX(1000)
        pieChart.animateY(1000)

        pieChart.notifyDataSetChanged()
        pieChart.invalidate()

    }

    @JvmStatic
    @BindingAdapter("setSolvedProblemTypeBarChart")
    fun setSolvedProblemTypeBarChart(barChart: HorizontalBarChart, problems: Problems?) {
        Timber.e(problems?.problemList.toString())
        val solvedType =MpChartUtils.getTypeStats(problems)
        // TODO : 실제 데이터 repository 로 했을때 그래프가 이상하게 그려지는 이슈 확인해보기
        barChart.run {
            setDrawBarShadow(true) // 그래프 그림자
            setTouchEnabled(false) // 차트 터치 막기
            setDrawValueAboveBar(true) // 입력?값이 차트 위or아래에 그려질 건지 (true=위, false=아래)
            setPinchZoom(false) // 두손가락으로 줌 설정
            setDrawGridBackground(false) // 격자구조
            setMaxVisibleValueCount(2) // 그래프 최대 갯수
            description.isEnabled = false // 그래프 오른쪽 하단에 라벨 표시
            legend.isEnabled = false // 차트 범례 설정(legend object chart)

            xAxis.run { // 아래 라벨 x축
                isEnabled = true // 라벨 표시 설정
                position = XAxis.XAxisPosition.BOTTOM // 라벨 위치 설정
                valueFormatter =
                    MpChartUtils.LabelCustomFormatter(solvedType?.keys!!.toList()) // 라벨 값 포멧 설정
                setDrawGridLines(false) // 격자구조
                granularity = 1f // 간격 설정
                setDrawAxisLine(false) // 그림
                textSize = 12f // 라벨 크기
                textColor = Color.BLUE
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

        solvedType?.onEachIndexed{ index,it ->
            values.add(BarEntry(index.toFloat(),it.value.toFloat()))
        }
//        for (i in 6..10) {
//            val index = i.toFloat()
//            val random = Random().nextInt(101).toFloat()
//
//            values.add(BarEntry(index, random))
//        }

        val set = BarDataSet(values, "내용없음")
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

        barChart.data = datas
    }
    @JvmStatic
    @BindingAdapter("setDateInfoGridList")
    fun setDateInfoGridList(recyclerView: RecyclerView,dates:MutableList<DateInfo>?){
        val adapter = recyclerView.adapter as? KUserDateInfoAdapter
        adapter?.setList(dates)
    }
}