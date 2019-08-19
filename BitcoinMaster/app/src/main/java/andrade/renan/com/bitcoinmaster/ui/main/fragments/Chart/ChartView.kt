package andrade.renan.com.bitcoinmaster.ui.main.fragments.Chart

import andrade.renan.com.bitcoinmaster.base.BaseView
import andrade.renan.com.bitcoinmaster.model.MarketPrice

interface ChartView: BaseView {


    fun showValidationMessage(errorCode: Int)
    fun openMainActivity()

    fun loadMarketPrice(marketPrice: MarketPrice?)

}
