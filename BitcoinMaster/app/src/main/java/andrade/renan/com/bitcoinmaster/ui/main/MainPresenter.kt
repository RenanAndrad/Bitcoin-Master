package andrade.renan.com.bitcoinmaster.ui.main

import andrade.renan.com.bitcoinmaster.base.BasePresenter
import andrade.renan.com.bitcoinmaster.model.MarketPrice
import andrade.renan.com.bitcoinmaster.webservice.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {


    fun loadMarketPrice(period: String) {
        val call = RetrofitInit().getMarketPriceService().getMarketPrice(period)

        call.enqueue(object : Callback<MarketPrice> {
            override fun onResponse(call: Call<MarketPrice>, response: Response<MarketPrice>) {

                if (response.isSuccessful) {
                    view.loadMarketPrice(response.body())
                }

            }

            override fun onFailure(call: Call<MarketPrice>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

//    fun setDataChart(marketPrice: MarketPrice, chart: Chart){
//
//        val count:Int
//        val range: Float
//
//        count = 45
//        range = 180F
//
//
//        val values = ArrayList()
//
//        for (i in 0 until count) {
//
//            val `val` = (Math.random() * range) as Float - 30
//            values.add(Entry(i, `val`, getResources().getDrawable(R.drawable.star)))
//        }
//
//        val set1: LineDataSet
//
//        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
//            set1 = chart.getData().getDataSetByIndex(0)
//            set1.values = values
//            set1.notifyDataSetChanged()
//            chart.getData().notifyDataChanged()
//            chart.notifyDataSetChanged()
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
//            set1.setCircleColor(Color.BLACK)
//
//            // line thickness and point size
//            set1.lineWidth = 1f
//            set1.circleRadius = 3f
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
//                IFillFormatter { dataSet, dataProvider -> chart.getAxisLeft().getAxisMinimum() }
//
//            // set color of filled area
//            if (Utils.getSDKInt() >= 18) {
//                // drawables only supported on api level 18 and above
//                val drawable = ContextCompat.getDrawable(this, R.drawable.fade_red)
//                set1.fillDrawable = drawable
//            } else {
//                set1.fillColor = Color.BLACK
//            }
//
//            val dataSets = ArrayList()
//            dataSets.add(set1) // add the data sets
//
//            // create a data object with the data sets
//            val data = LineData(dataSets)
//
//            // set data
//            chart.setData(data)
//        }
//
//    }


}
