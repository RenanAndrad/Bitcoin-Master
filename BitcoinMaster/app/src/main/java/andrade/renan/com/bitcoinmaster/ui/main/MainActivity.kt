package andrade.renan.com.bitcoinmaster.ui.main

import andrade.renan.com.bitcoinmaster.base.BaseActivity
import andrade.renan.com.bitcoinmaster.model.MarketPrice
import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.model.PointGraph
import andrade.renan.com.bitcoinmaster.ui.main.fragments.Chart.ChartFragment
import andrade.renan.com.bitcoinmaster.ui.main.fragments.Main.MainFragment
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlin.collections.ArrayList








class MainActivity : BaseActivity<MainPresenter>(), MainView, View.OnClickListener, OnChartValueSelectedListener {


    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }


    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }


    override fun loadMarketPrice(marketPrice: MarketPrice?) {
        if (marketPrice != null) {
            setDataChart(marketPrice.values)
        }
    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(MainFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragment(ChartFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun setchart(){
//        marketPriceChart.setBackgroundColor(Color.WHITE)
//
//        marketPriceChart.getDescription().setEnabled(false)
//        marketPriceChart.setTouchEnabled(true)
//
//        marketPriceChart.setDrawGridBackground(false)
//        marketPriceChart.setPinchZoom(true)
//
//
//        marketPriceChart.getXAxis().setValueFormatter(DateFormatterChart())
//        marketPriceChart.setVisibleXRange(1f,5f)

    }

    private fun loadMainFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.detailsFragment, MainFragment(), "")

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.detailsFragment, fragment, "").commit()
    }

    fun setDataChart(graphPrice: List<PointGraph>?){


        val values = ArrayList<Entry>()
        if (graphPrice != null) {
            for (i in 0 until graphPrice.size) {
                val value = graphPrice.get(i).y
                values.add(Entry(graphPrice.get(i).x.toFloat(), value.toFloat()))
//                values.add("".toFloat().toInt(),value);
            }


        }



//        val set1: LineDataSet

//        if (marketPriceChart.getData() != null && marketPriceChart.getData().getDataSetCount() > 0) {
//            set1 = marketPriceChart.getData().getDataSetByIndex(0) as LineDataSet
//            set1.values = values
//            set1.notifyDataSetChanged()
//            marketPriceChart.getData().notifyDataChanged()
//            marketPriceChart.notifyDataSetChanged()
//        } else {
//            // create a dataset and give it a type
//            set1 = LineDataSet(values, "DataSet 1")
//
//            set1.setDrawIcons(false)
//
//            // draw dashed line
//            set1.enableDashedLine(10f, 5f, 0f)
//
//            // black lines and points
//            set1.color = Color.BLACK
////            set1.setCircleColor(Color.BLACK)
//
//            // line thickness and point size
//            set1.lineWidth = 1f
////            set1.circleRadius = 3f
//            set1.setDrawCircleHole(false)
//
//            // draw points as solid circles
//            set1.setDrawCircleHole(false)
//
//            // customize legend entry
//            set1.formLineWidth = 1f
//            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
//            set1.formSize = 15f
//
//            // text size of values
//            set1.valueTextSize = 9f
//
//            // draw selection line as dashed
//            set1.enableDashedHighlightLine(10f, 5f, 0f)
//
//            // set the filled area
//            set1.setDrawFilled(true)
//            set1.fillFormatter =
//                IFillFormatter { dataSet, dataProvider -> marketPriceChart.getAxisLeft().getAxisMinimum() }
//
//            // set color of filled area
//            if (Utils.getSDKInt() >= 18) {
//                // drawables only supported on api level 18 and above
//                val drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue)
//                set1.fillDrawable = drawable
//            } else {
//                set1.fillColor = Color.BLACK
//            }
//
//            val dataSets = ArrayList<ILineDataSet>()
//            dataSets.add(set1) // add the data sets
//
//            // create a data object with the data sets
//            val data = LineData(dataSets)
//
//            // set data
//            marketPriceChart.setData(data)
//        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        loadMainFragment()

    }


}

