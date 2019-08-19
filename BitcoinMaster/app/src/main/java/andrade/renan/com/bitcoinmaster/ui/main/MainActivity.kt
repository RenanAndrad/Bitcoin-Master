package andrade.renan.com.bitcoinmaster.ui.main

import andrade.renan.com.bitcoinmaster.base.BaseActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.ui.main.fragments.Chart.ChartFragment
import andrade.renan.com.bitcoinmaster.ui.main.fragments.Stats.StatsFragment
import androidx.fragment.app.Fragment


class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        loadMainFragment()

    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(StatsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragment(ChartFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadMainFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.detailsFragment, StatsFragment(), "")

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.detailsFragment, fragment, "").commit()
    }

    override fun showError(error: String) {
        this.toast(error)
    }

    override fun upadateData() {
        this.toast(getString(R.string.update_date_messsage))
    }

    override fun isLoading(): Boolean {
        return true
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }


    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }


}

