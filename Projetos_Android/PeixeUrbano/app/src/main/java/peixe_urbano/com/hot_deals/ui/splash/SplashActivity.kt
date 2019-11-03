package peixe_urbano.com.hot_deals.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import peixe_urbano.com.hot_deals.R
import peixe_urbano.com.hot_deals.databinding.ActivitySplashBinding
import peixe_urbano.com.hot_deals.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {

    private var mBinding:ActivitySplashBinding? = null
    private var mSplashViewModel:SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        actions()

    }


    private fun initViewModel(){

        mSplashViewModel = SplashViewModel(application)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        mBinding!!.viewModel = mSplashViewModel
        mBinding!!.executePendingBindings()

        this.lifecycle.addObserver(mSplashViewModel!!)
        mBinding!!.lifecycleOwner = this
    }


    private fun actions(){

        mSplashViewModel?.finishCoolDownObserver?.observe(this, Observer {
            when (it) {
                true -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        })
    }




}
