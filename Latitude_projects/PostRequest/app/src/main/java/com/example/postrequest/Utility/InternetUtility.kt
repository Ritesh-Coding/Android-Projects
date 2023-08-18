package com.example.postrequest.Utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService

class InternetUtility {
    companion object
    {
        fun isInterntAvailable(context: Context):Boolean{
            (context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager).run{
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)?:false
                }
                else
                {
                    (@Suppress("Deprecations")
                            return this.activeNetworkInfo?.isConnected?: false)
                }
            }
        }

    }
}