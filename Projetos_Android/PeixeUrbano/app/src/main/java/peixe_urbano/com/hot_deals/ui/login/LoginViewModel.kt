package peixe_urbano.com.hot_deals.ui.login

import android.Manifest
import android.app.Application
import androidx.lifecycle.*
import com.facebook.CallbackManager
import peixe_urbano.com.hot_deals.repository.FacebookRepo
import peixe_urbano.com.hot_deals.model.ActivityEnum
import peixe_urbano.com.hot_deals.model.FacebookFieldsEnum
import peixe_urbano.com.hot_deals.model.User
import peixe_urbano.com.hot_deals.util.HelperPermission
import android.R
import androidx.databinding.BindingAdapter
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class LoginViewModel(application: Application, helperPermission: HelperPermission, facebookRepo: FacebookRepo) : AndroidViewModel(application),
    LifecycleObserver {

    val openActivityObserver = MutableLiveData<Int>()
    var user = MutableLiveData<User>()

    private var mHelperPermission: HelperPermission? = null
    private var mFacebookRepo: FacebookRepo? = null

    init {
        mHelperPermission = helperPermission
        mFacebookRepo = facebookRepo

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        setPermissions()
        mFacebookRepo!!.initFacebook(listOf(FacebookFieldsEnum.PUBLIC_PROFILE.field,
            FacebookFieldsEnum.EMAIL.field), user)
    }

    fun openDeals(){
        if(mHelperPermission!!.checkPermissions()) {
            openActivityObserver.value = ActivityEnum.MAIN.activity
        }else{
            mHelperPermission!!.requestPermissions(0)
        }
    }


    fun onRequestPermissionsResult(permissions:Array<String>,grantResults: IntArray?){
        if (mHelperPermission!!.areAllRequiredPermissionsGranted(permissions, grantResults)) {
            openActivityObserver.value = ActivityEnum.MAIN.activity
        }
    }

    private fun setPermissions() {
        //todo: check o Manifest component
        val permissionsList: Array<String>  = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
        mHelperPermission!!.setRequiredPermissions(permissionsList)
    }


}