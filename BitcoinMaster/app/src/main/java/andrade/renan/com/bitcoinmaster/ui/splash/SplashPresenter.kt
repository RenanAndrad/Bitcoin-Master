package andrade.renan.com.bitcoinmaster.ui.main

import andrade.renan.com.bitcoinmaster.base.BasePresenter
import android.os.CountDownTimer

class SplashPresenter(splashView: SplashView) : BasePresenter<SplashView>(splashView) {


    fun startCounter() {

        val timer = object : CountDownTimer(2400, 500) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                view.openMainActivity()
            }
        }
        timer.start()
    }
}
