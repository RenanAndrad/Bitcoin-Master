package peixe_urbano.com.hot_deals.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import peixe_urbano.com.hot_deals.R
import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.facebook.CallbackManager
import peixe_urbano.com.hot_deals.MainActivity
import peixe_urbano.com.hot_deals.data.ImageViewBindingAdapter
import peixe_urbano.com.hot_deals.databinding.ActivityLoginBinding
import peixe_urbano.com.hot_deals.repository.FacebookRepo
import peixe_urbano.com.hot_deals.model.ActivityEnum
import peixe_urbano.com.hot_deals.util.HelperPermission


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var mCallbackManager = CallbackManager.Factory.create()

    private var mBinding:ActivityLoginBinding? = null
    private var mLoginViewModel:LoginViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViewModel()
        actions()
        setListeners()



    }

    private fun initViewModel(){

        mLoginViewModel = LoginViewModel(application, HelperPermission(this), FacebookRepo.initComponents(mCallbackManager, btnFacebook))
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding!!.viewModel = mLoginViewModel
        mBinding!!.executePendingBindings()

        this.lifecycle.addObserver(mLoginViewModel!!)
        mBinding!!.lifecycleOwner = this
    }

    private fun setListeners(){
        btnContinue.setOnClickListener(this)
        btnFacebook.setOnClickListener(this)
    }

    private fun actions(){

        mLoginViewModel?.openActivityObserver?.observe(this, Observer {
            when (it) {
                ActivityEnum.MAIN.activity -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        })

        mLoginViewModel?.user?.observe(this, Observer {
            when (it.success) {
                false-> {
                    ImageViewBindingAdapter.loadImage(ivProfileImage,it!!.picture!!.data!!.url)
                    //todo: continue
                }
                true->{
                    //todo: continue
                }
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mLoginViewModel?.onRequestPermissionsResult(permissions,grantResults)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnContinue -> {
                mLoginViewModel!!.openDeals()
            }
            R.id.btnFacebook -> {
            }
        }
    }
}
