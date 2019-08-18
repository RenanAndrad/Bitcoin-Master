package andrade.renan.com.bitcoinmaster.ui.splash

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BaseActivity
import andrade.renan.com.bitcoinmaster.ui.main.*
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {

    override fun instantiatePresenter(): SplashPresenter {
        return SplashPresenter(this)    }


    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        setFonts()
    }


    fun setFonts() {
        titleTv.setTypeface(sOpenSansFontBold);
    }

    override fun onStart() {
        super.onStart()
        presenter.startCounter()
    }

}
