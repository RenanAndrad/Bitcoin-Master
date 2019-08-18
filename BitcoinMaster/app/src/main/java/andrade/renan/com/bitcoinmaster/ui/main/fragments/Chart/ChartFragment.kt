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
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.fragment_chart.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ChartFragment : BaseFragment<ChartPresenter>(),
    ChartView, View.OnClickListener, OnChartValueSelectedListener {

    private lateinit var mTitleHistory: TextView
    private lateinit var mButtonToday: Button
    private lateinit var mButtonWeek: Button
    private lateinit var mButtonMonth: Button
    private lateinit var mButtonSixMonth: Button
    private lateinit var mChart: LineChart

    private var param1: String? = null
    private var param2: String? = null


    override fun showValidationMessage(errorCode: Int) {
    }

    override fun openMainActivity() {
    }

    override fun showError() {
    }

    override fun showLoading() {
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
            R.id.buttonToday -> {
                presenter.loadMarketPrice(this.getString(R.string.one_day))
            }
            R.id.buttonWeek -> {
                presenter.loadMarketPrice(this.getString(R.string.one_week))
            }
            R.id.buttonMonth -> {
                presenter.loadMarketPrice(this.getString(R.string.one_mouth))
            }
            R.id.buttonSixMonth -> {
                presenter.loadMarketPrice(this.getString(R.string.six_mouths))
            }
        }
    }

    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

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
        return rootView

    }

    fun setDataChart(graphPrice: List<PointGraph>?) {


        val values = ArrayList<Entry>()
        if (graphPrice != null) {
            for (i in 0 until graphPrice.size) {
                val value = graphPrice.get(i).y
                values.add(Entry(graphPrice.get(i).x.toFloat(), value.toFloat()))
            }


        }


        val set1: LineDataSet

        if (marketPriceChart.getData() != null && marketPriceChart.getData().getDataSetCount() > 0) {
            set1 = marketPriceChart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            marketPriceChart.getData().notifyDataChanged()
            marketPriceChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")

            set1.setDrawIcons(false)

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points
            set1.color = Color.BLACK
//            set1.setCircleColor(Color.BLACK)

            // line thickness and point size
            set1.lineWidth = 1f
//            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> marketPriceChart.getAxisLeft().getAxisMinimum() }

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
//                val drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue)
//                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            marketPriceChart.setData(data)
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

        mTitleHistory.setTypeface(sUbuntuFontRegular)

        mButtonToday.setTypeface(sUbuntuFontLight)
        mButtonWeek.setTypeface(sUbuntuFontLight)
        mButtonMonth.setTypeface(sUbuntuFontLight)
        mButtonSixMonth.setTypeface(sUbuntuFontLight)


    }

    private fun iniViews(view: View) {

        mTitleHistory = view.findViewById<TextView>(R.id.titleHistory)

        mButtonToday = view.findViewById<Button>(R.id.buttonToday)
        mButtonToday.setOnClickListener(this)
        mButtonWeek = view.findViewById<Button>(R.id.buttonWeek)
        mButtonWeek.setOnClickListener(this)
        mButtonMonth = view.findViewById<Button>(R.id.buttonMonth)
        mButtonMonth.setOnClickListener(this)
        mButtonSixMonth = view.findViewById<Button>(R.id.buttonSixMonth)
        mButtonSixMonth.setOnClickListener(this)

        mChart = view.findViewById<LineChart>(R.id.marketPriceChart)

    }

}
