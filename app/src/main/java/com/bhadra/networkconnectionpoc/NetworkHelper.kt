package com.bhadra.networkconnectionpoc

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import kotlin.math.log

object NetworkHelper {

     fun checkInternetConnection(context: Context) : Boolean {
        // Whether there is a Wi-Fi connection.
        var wifiConnected = false
        // Whether there is a mobile connection.
        var mobileConnected = false
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isMetered = connMgr.isActiveNetworkMetered()
         Log.d(">>>isMetered ","->$isMetered")
        val activeInfo: NetworkInfo? = connMgr.activeNetworkInfo
        if (activeInfo?.isConnected == true) {
            wifiConnected = activeInfo.type == ConnectivityManager.TYPE_WIFI
            mobileConnected = activeInfo.type == ConnectivityManager.TYPE_MOBILE
        } else {
            wifiConnected = false
            mobileConnected = false
        }
         Log.d(">>>iswifiConnected ","->$wifiConnected")
         Log.d(">>>ismobileConnected","->$mobileConnected")
        return wifiConnected||mobileConnected
    }
}