package peixe_urbano.com.hot_deals.repository

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.crashlytics.android.answers.LoginEvent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import peixe_urbano.com.hot_deals.model.User
import com.google.gson.JsonElement



object FacebookRepo {

    private var mCallbackManager = CallbackManager.Factory.create()
    private var mLoginButton: LoginButton? = null


    fun initComponents(callbackManager: CallbackManager, loginButton: LoginButton): FacebookRepo {
        mCallbackManager = callbackManager
        mLoginButton = loginButton
        return this
    }

    fun initFacebook(permissions: List<String>, userLiveData: MutableLiveData<User>) {
        mLoginButton!!.setPermissions(
            permissions
        )

        mLoginButton!!.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(
                    loginResult.accessToken
                ) { `object`, response ->

                    try {
                        var contentJson: String = response.rawResponse
                        val user:User = Gson().fromJson(contentJson, User::class.java)

                        user.picture!!.data!!.url = "https://graph.facebook.com/" + user.id+ "/picture?height=150"
                        userLiveData.postValue(user)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,birthday,picture")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                Log.i("", "onCancel login Facebook")
            }

            override fun onError(exception: FacebookException) {
                Log.i("", "onCancel login Facebook")
                val user = User()
                user.success = false
                userLiveData.postValue(user)
            }
        })
    }
}