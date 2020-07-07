package com.bhadra.networkconnectionpoc

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bhadra.networkconnectionpoc.NetworkHelper.checkInternetConnection
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :BaseActivity(){

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_checknetwork.setOnClickListener {
           if(!checkInternetConnection(this)){
                onNetworkConnectionChanged(false)
           }else{
               onNetworkConnectionChanged(true)
           }

        }
    }
    override fun onStart() {
        super.onStart()
        NetWorkStateReceiver.registerReceiver(this,this)

    }

    override fun onStop() {
        super.onStop()
        NetWorkStateReceiver.unregisterReceiver(this)
    }
}