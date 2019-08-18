package andrade.renan.com.bitcoinmaster.ui.main.fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BaseFragment
import andrade.renan.com.bitcoinmaster.model.MarketPrice
import andrade.renan.com.bitcoinmaster.ui.main.MainPresenter
import andrade.renan.com.bitcoinmaster.ui.main.MainView
import android.widget.TextView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : BaseFragment<MainPresenter>(), MainView, View.OnClickListener, OnChartValueSelectedListener {

    private lateinit var  mTitleActualPrice: TextView
    private lateinit var  mTitleBitcoin: TextView
    private lateinit var  mDolarSymbol: TextView
    private lateinit var  mValueBitcoin: TextView


    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun loadMarketPrice(marketPrice: MarketPrice?) {
    }

    override fun onClick(p0: View?) {
    }

    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

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
        var rootView = inflater.inflate(R.layout.fragment_main, container, false)

        rootView.context
        iniViews(rootView)
        setFonts()
        return rootView
    }

    private fun setFonts() {
        mTitleActualPrice.setTypeface(sUbuntuFontRegular)
        mTitleBitcoin.setTypeface(sUbuntuFontBold)

        mDolarSymbol.setTypeface(sUbuntuFontBold)
        mValueBitcoin.setTypeface(sUbuntuFontBold)
    }


    private fun iniViews(view: View) {

        mTitleActualPrice= view.findViewById<TextView>(R.id.titleActualPrice)
        mTitleBitcoin= view.findViewById<TextView>(R.id.titleBitcoin)
        mDolarSymbol = view.findViewById<TextView>(R.id.dolarSymbol)
        mValueBitcoin = view.findViewById<TextView>(R.id.valueBitcoin)

    }
}
