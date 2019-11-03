package peixe_urbano.com.hot_deals.ui.splash

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.*

class SplashViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    val finishCoolDownObserver = MutableLiveData<Boolean>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        startCoolDown()
    }

    private fun startCoolDown() {

        val timer = object : CountDownTimer(5000, 5000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                finishCoolDownObserver.value = true
            }
        }
        timer.start()
    }



}