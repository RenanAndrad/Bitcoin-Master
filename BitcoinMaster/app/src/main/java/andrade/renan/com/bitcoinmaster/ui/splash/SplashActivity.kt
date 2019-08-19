package andrade.renan.com.bitcoinmaster.ui.splash

import andrade.renan.com.bitcoinmaster.R
import andrade.renan.com.bitcoinmaster.base.BaseActivity
import andrade.renan.com.bitcoinmaster.ui.main.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        setFonts()
    }

    override fun onStart() {
        super.onStart()
        presenter.startCounter()
    }


    fun setFonts() {
        titleTv.setTypeface(sOpenSansFontBold);
    }


    override fun instantiatePresenter(): SplashPresenter {
        return SplashPresenter(this)
    }


    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showError(error: String) {
        fun Context.toast(message: CharSequence) =
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun upadateData() {
        fun Context.toast(message: CharSequence) =
            Toast.makeText(this, "Dados atualizados", Toast.LENGTH_SHORT).show()
    }

    override fun isLoading(): Boolean {
        return true
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }


}
