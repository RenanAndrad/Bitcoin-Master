package andrade.renan.com.bitcoinmaster.ui.main.fragments.Stats

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BasePresenter
import andrade.renan.com.bitcoinmaster.model.Bitcoin
import andrade.renan.com.bitcoinmaster.webservice.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class StatsPresenter(statsView: StatsView) : BasePresenter<StatsView>(statsView) {


  fun loadSats(contex:StatsFragment) {

        if (!view.isLoading()) {
            view.showLoading()

            val call = RetrofitInit().getService().getStats()

            call.enqueue(object : Callback<Bitcoin> {
                override fun onResponse(call: Call<Bitcoin>, response: Response<Bitcoin>) {

                    if (response.isSuccessful) {
                        view.hideLoading()
                        view.loadStatsView(formatDollar(response.body()?.market_price_usd))
                    } else {

                        view.hideLoading()
                        view.showError(contex.getString(R.string.error_message))
                    }
                }

                override fun onFailure(call: Call<Bitcoin>, t: Throwable) {
                    view.hideLoading()
                    view.hideValue()
                    view.showError(contex.getString(R.string.error_message))
                    t.printStackTrace()
                }
            })
        }
    }

    private fun formatDollar(price:Double?):String{
        val format = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(2)
        format.setCurrency(Currency.getInstance("USD"))

        return  format.format(price)

    }


}
