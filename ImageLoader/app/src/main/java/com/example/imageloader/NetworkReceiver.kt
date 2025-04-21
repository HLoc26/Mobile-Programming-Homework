@file:Suppress("DEPRECATION")

package com.example.imageloader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkReceiver(
    private val onNetworkChange: (connected: Boolean, isWifi: Boolean) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val (connected, isWifi) = checkConnection(context)
        onNetworkChange(connected, isWifi)
    }

    private fun checkConnection(context: Context): Pair<Boolean, Boolean> {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = cm.activeNetwork ?: return Pair(false, false)
            val capabilities = cm.getNetworkCapabilities(network) ?: return Pair(false, false)
            val isConnected = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            val isWifi = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            return Pair(isConnected, isWifi)
        } else {
            val networkInfo = cm.activeNetworkInfo
            val isConnected = networkInfo?.isConnected == true
            val isWifi = networkInfo?.type == ConnectivityManager.TYPE_WIFI
            return Pair(isConnected, isWifi)
        }
    }
}
