package com.bhadra.networkconnectionpoc

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*


object NetWorkStateReceiver : BroadcastReceiver() {
    var broadcastReceiver: BroadcastReceiver? = null

    var onNetworkConnectionChangedListener: OnNetworkConnectionChangedListener? = null

    var isConnected = false

    init {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkRequest = NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_LOWPAN)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)
                    .build()

                    connectivityManager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback() {
                        override fun onAvailable(network: Network) {
                            //  Variables.isNetworkConnected = true // Global Static Variable
                        }
                        override fun onCapabilitiesChanged(
                            network: Network,
                            networkCapabilities: NetworkCapabilities
                        ) {

                            if(!networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)){
                                isConnected = false
                                onNetworkConnectionChangedListener?.onNetworkConnectionChanged(isConnected)
                            }

                        }
                        override fun onUnavailable() {
                            super.onUnavailable()
                            isConnected = false
                            onNetworkConnectionChangedListener?.onNetworkConnectionChanged(isConnected)
                        }
                        override fun onLost(network: Network) {
                            isConnected = false
                            onNetworkConnectionChangedListener?.onNetworkConnectionChanged(isConnected)
                        }
                    })
            }
        }
    }

    fun registerReceiver(context: Context,activity: Activity) {
        if(activity is OnNetworkConnectionChangedListener)
            onNetworkConnectionChangedListener = activity
        context.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    fun unregisterReceiver(context: Context) {
        onNetworkConnectionChangedListener = null
        context.unregisterReceiver(broadcastReceiver)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.sendBroadcast(Intent(ConnectivityManager.CONNECTIVITY_ACTION))
    }
}