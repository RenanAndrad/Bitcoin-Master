package andrade.renan.com.bitcoinmaster.ui.main.fragments.Stats

import andrade.renan.com.bitcoinmaster.base.BaseView

interface StatsView: BaseView {

    fun loadStatsView(bitcoinPrice: String)

    fun showValue()
    fun hideValue()

}
