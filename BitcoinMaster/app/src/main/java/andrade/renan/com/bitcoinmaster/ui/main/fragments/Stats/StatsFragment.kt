package andrade.renan.com.bitcoinmaster.ui.main.fragments.Stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BaseFragment
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StatsFragment : BaseFragment<StatsPresenter>(), StatsView, View.OnClickListener {


    private lateinit var mTitleActualPrice: TextView
    private lateinit var mTitleBitcoin: TextView
    private lateinit var mValueBitcoin: TextView
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
        var rootView = inflater.inflate(R.layout.fragment_stats, container, false)

        rootView.context
        iniViews(rootView)
        setFonts()

        presenter.loadSats(this)
        return rootView
    }

    override fun loadStatsView(bitcoinPrice: String) {
        mValueBitcoin.setText(bitcoinPrice)
    }


    private fun setFonts() {
        mTitleActualPrice.setTypeface(sUbuntuFontRegular)
        mTitleBitcoin.setTypeface(sUbuntuFontBold)

        mValueBitcoin.setTypeface(sUbuntuFontBold)
    }


    private fun iniViews(view: View) {

        mTitleActualPrice = view.findViewById<TextView>(R.id.titleActualPrice)
        mTitleBitcoin = view.findViewById<TextView>(R.id.titleBitcoin)
        mValueBitcoin = view.findViewById<TextView>(R.id.valueBitcoin)
        mProgressBar = view.findViewById<ProgressBar>(R.id.progressBarStats)


    }


    override fun instantiatePresenter(): StatsPresenter {
        return StatsPresenter(this)
    }

    override fun onClick(p0: View?) {
    }

    override fun showError(error: String) {
        context?.toast(this.getString(R.string.error_message))
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

    override fun showValue() {
        mProgressBar.setVisibility(View.VISIBLE)
    }

    override fun hideValue() {
        mValueBitcoin.setText(this.getString(R.string.value_bitcoin_defautl))
    }


}
