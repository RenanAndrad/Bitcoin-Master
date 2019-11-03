package peixe_urbano.com.hot_deals.util

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import peixe_urbano.com.hot_deals.R
import peixe_urbano.com.hot_deals.util.message.MessageUtil
import java.util.ArrayList
import java.util.LinkedHashMap

class HelperPermission(
) {
    private val TAG = "HelperPermission"
    private val PERMISSIONS_REQUEST_CODE = 1
    private var mContext: Activity? = null
    private var mRequiredPermissions: Array<String> = emptyArray()
    private val mPermissionsToRequest = ArrayList<String>()


    constructor( context: Activity) : this() {
        this.mContext = context

    }


     fun setRequiredPermissions(requiredPermissions: Array<String>){
        this.mRequiredPermissions = requiredPermissions
    }


    fun checkPermissions(): Boolean {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)
            return true

        for (permission in mRequiredPermissions) {
            if (ContextCompat.checkSelfPermission(
                    mContext!!,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                mPermissionsToRequest.add(permission)
            }
        }

        return mPermissionsToRequest.isEmpty()

    }


    fun requestPermissions(requestCode: Int) {
        val request = mPermissionsToRequest.toTypedArray()

        val log = StringBuilder()
        log.append("Requesting permissions: ")

        for (permission in request) {
            log.append(permission).append(" ")
        }

        Log.i(javaClass.simpleName, log.toString())

        mContext?.let { ActivityCompat.requestPermissions(it, request, requestCode) }
    }


    fun areAllRequiredPermissionsGranted(
        permissions: Array<String>?,
        grantResults: IntArray?
    ): Boolean {
        if (permissions == null || permissions.isEmpty()
            || grantResults == null || grantResults.isEmpty()
        ) {
            return false
        }

        val perms = LinkedHashMap<String, Int>()

        for (i in permissions.indices) {
            if (!perms.containsKey(permissions[i]) || perms.containsKey(permissions[i]) && perms[permissions[i]] == PackageManager.PERMISSION_DENIED)
                perms[permissions[i]] = grantResults[i]
        }

        for ((_, value) in perms) {
            if (value != PackageManager.PERMISSION_GRANTED) {

                verifyOnePermission(permissions)
                return false
            }
        }

        return true
    }

    private fun verifyOnePermission(permissions: Array<String>): Boolean {

        var neverAsk: Boolean
        for (perm in permissions) {
            neverAsk = isCheckedNeverAskAgain(perm)
            if (neverAsk) {
                MessageUtil.alert(mContext!!,
                    "",
                    mContext!!.getString(R.string.generic_permissions),
                    mContext!!.getString(R.string.ok),
                    DialogInterface.OnClickListener { dialog, which -> openSettingsScreen() },
                    "",
                    DialogInterface.OnClickListener { dialog, which -> }).show()
                return false
            }
        }
        return true
    }


    private fun isCheckedNeverAskAgain(permissionName: String): Boolean {
        val isNeverAskAgain =
            !ActivityCompat.shouldShowRequestPermissionRationale(mContext!!, permissionName)
        if (!isNeverAskAgain) {
            Log.i(TAG, "Permission not checked never as again $permissionName")
        } else {
            Log.i(TAG, "Permission checked never ask again $permissionName")
        }
        return isNeverAskAgain
    }

    private fun openSettingsScreen() {
        val settings = Intent()
        settings.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.parse("package:" + mContext!!.packageName)
        settings.data = uri
        mContext!!.startActivity(settings)
    }
}