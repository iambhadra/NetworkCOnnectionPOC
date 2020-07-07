package com.bhadra.networkconnectionpoc

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity(),OnNetworkConnectionChangedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if(isConnected){
            val snackbar = Snackbar.make( findViewById(R.id.sb_snackbar),"Network Unavailable",
                Snackbar.LENGTH_LONG).setAction("Settings", clickListener)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.LTGRAY)
            val textView =
                snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.BLUE)
            textView.textSize = 21f
            snackbar.show()
        }
    }
    val clickListener = View.OnClickListener { view ->
        startActivity(Intent(Settings.ACTION_SETTINGS))
    }
}