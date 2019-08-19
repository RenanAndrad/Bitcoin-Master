package andrade.renan.com.bitcoinmaster.ui.main.fragments.Chart

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BasePresenter
import andrade.renan.com.bitcoinmaster.model.MarketPrice
import andrade.renan.com.bitcoinmaster.webservice.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartPresenter(chartView: ChartView) : BasePresenter<ChartView>(chartView) {


    fun loadMarketPrice(period: String, contex:ChartFragment) {

        if (!view.isLoading()) {
            view.showLoading()

            val call = RetrofitInit().getService().getMarketPrice(period)

            call.enqueue(object : Callback<MarketPrice> {
                override fun onResponse(call: Call<MarketPrice>, response: Response<MarketPrice>) {

                    if (response.isSuccessful) {
                        view.loadMarketPrice(response.body())
                    } else {
                        view.hideLoading()
                        view.showError(contex.getString(R.string.error_message))
                    }
                }

                override fun onFailure(call: Call<MarketPrice>, t: Throwable) {
                    view.hideLoading()
                    view.showError(contex.getString(R.string.error_message))
                    t.printStackTrace()
                }
            })
        }
    }


}
