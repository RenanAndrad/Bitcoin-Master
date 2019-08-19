package andrade.renan.com.bitcoinmaster.ui.main.fragments.Chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BaseFragment
import andrade.renan.com.bitcoinmaster.model.MarketPrice
import andrade.renan.com.bitcoinmaster.model.PointGraph
import andrade.renan.com.bitcoinmaster.util.DateFormatterChart
import android.graphics.Color
import android.graphics.DashPathEffect
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ChartFragment : BaseFragment<ChartPresenter>(),
    ChartView, View.OnClickListener {


    private lateinit var mTitleHistory: TextView
    private lateinit var mButtonThreeMonths: Button
    private lateinit var mButtonWeek: Button
    private lateinit var mButtonMonth: Button
    private lateinit var mButtonSixMonth: Button
    private lateinit var mChart: LineChart
    private lateinit var mProgressBar: ProgressBar

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_chart, container, false)

        rootView.context
        iniViews(rootView)
        setFonts()
        setchart()
        loadChartDefault()
        return rootView

    }

    private fun setDataChart(graphPrice: List<PointGraph>?) {


        val values = ArrayList<Entry>()
        if (graphPrice != null) {
            for (i in 0 until graphPrice.size) {
                val value = graphPrice.get(i).y
                values.add(Entry(graphPrice.get(i).x.toFloat(), value.toFloat()))
            }
        }

        val lineDataSet: LineDataSet

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            lineDataSet = mChart.getData().getDataSetByIndex(0) as LineDataSet
            lineDataSet.values = values
            lineDataSet.notifyDataSetChanged()
            mChart.getData().notifyDataChanged()
            mChart.notifyDataSetChanged()
            mChart.invalidate()
            hideLoading()

        } else {
            lineDataSet = LineDataSet(values, this.getString(R.string.description_chart))
            lineDataSet.disableDashedLine()
            lineDataSet.setDrawIcons(false)
            lineDataSet.enableDashedLine(10f, 5f, 0f)
            lineDataSet.lineWidth = 1f
            lineDataSet.setDrawCircleHole(false)
            lineDataSet.setDrawCircleHole(false)
            lineDataSet.formLineWidth = 1f
            lineDataSet.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            lineDataSet.formSize = 15f
            lineDataSet.valueTextSize = 9f
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f)
            lineDataSet.setDrawFilled(true)
            lineDataSet.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> mChart.getAxisLeft().getAxisMinimum() }
            lineDataSet.setFillColor(Color.BLUE)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)

            val data = LineData(lineDataSet)

            mChart.setData(data)
            mChart.notifyDataSetChanged()
            mChart.invalidate()
            hideLoading()

        }


    }

    private fun setchart() {
        mChart.setBackgroundColor(Color.WHITE)

        mChart.getDescription().setEnabled(false)
        mChart.setTouchEnabled(true)

        mChart.setDrawGridBackground(false)
        mChart.setPinchZoom(true)


        mChart.getXAxis().setValueFormatter(DateFormatterChart())
        mChart.setVisibleXRange(1f, 5f)


    }


    private fun setFonts() {

        mTitleHistory.setTypeface(sUbuntuFontBold)

        mButtonThreeMonths.setTypeface(sUbuntuFontLight)
        mButtonWeek.setTypeface(sUbuntuFontLight)
        mButtonMonth.setTypeface(sUbuntuFontLight)
        mButtonSixMonth.setTypeface(sUbuntuFontLight)


    }

    private fun iniViews(view: View) {

        mTitleHistory = view.findViewById<TextView>(R.id.titleHistory)


        mButtonWeek = view.findViewById<Button>(R.id.buttonWeek)
        mButtonWeek.setOnClickListener(this)
        mButtonMonth = view.findViewById<Button>(R.id.buttonMonth)
        mButtonMonth.setOnClickListener(this)
        mButtonThreeMonths = view.findViewById<Button>(R.id.buttonThreeMonths)
        mButtonThreeMonths.setOnClickListener(this)
        mButtonSixMonth = view.findViewById<Button>(R.id.buttonSixMonths)
        mButtonSixMonth.setOnClickListener(this)

        mChart = view.findViewById<LineChart>(R.id.marketPriceChart)
        mProgressBar = view.findViewById<ProgressBar>(R.id.progressBar)

    }

    private fun loadChartDefault() {
        mButtonWeek.setPressed(true)
        presenter.loadMarketPrice(this.getString(R.string.one_week), this)
    }

    override fun showValidationMessage(errorCode: Int) {
    }

    override fun openMainActivity() {
    }

    override fun showError(error: String) {
        context?.toast(error)
    }

    override fun upadateData() {
        context?.toast(this.getString(R.string.update_date_messsage))
    }

    override fun isLoading(): Boolean {
        return mProgressBar.isVisible
    }

    override fun showLoading() {
        mProgressBar.setVisibility(View.VISIBLE)
    }

    override fun hideLoading() {
        mProgressBar.setVisibility(View.GONE)
    }

    override fun instantiatePresenter(): ChartPresenter {
        return ChartPresenter(this)
    }


    override fun loadMarketPrice(marketPrice: MarketPrice?) {
        if (marketPrice != null) {
            setDataChart(marketPrice.values)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonWeek -> {
                mButtonWeek.setPressed(true)
                presenter.loadMarketPrice(this.getString(R.string.one_week), this)
            }
            R.id.buttonMonth -> {
                mButtonMonth.setPressed(true)
                presenter.loadMarketPrice(this.getString(R.string.one_month), this)
            }
            R.id.buttonThreeMonths -> {
                mButtonThreeMonths.setPressed(true)
                presenter.loadMarketPrice(this.getString(R.string.three_months), this)
            }
            R.id.buttonSixMonths -> {
                mButtonSixMonth.setPressed(true)
                presenter.loadMarketPrice(this.getString(R.string.six_months), this)
            }
        }
    }


}
