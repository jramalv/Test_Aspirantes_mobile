package com.example.test_aspirantes_mobile.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import com.example.test_aspirantes_mobile.model.Movies
import com.example.test_aspirantes_mobile.model.Routes
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.concurrent.TimeUnit

class Utils {
    companion object{
        fun checkWIFI(context: Context): Boolean {
            var isWIFI: Boolean
            val network = isNetworkAvailable(context)
            isWIFI = network
            return isWIFI
        }

        @SuppressLint("ServiceCast")
        fun isNetworkAvailable(context: Context): Boolean {
            var haveConnectedWifi = false
            var haveConnectedMobile = false

            val cm =
                context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager?
            val netInfo = cm!!.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals("WIFI", ignoreCase = true))
                    if (ni.isConnected)
                        haveConnectedWifi = true
                if (ni.typeName.equals("MOBILE", ignoreCase = true))
                    if (ni.isConnected)
                        haveConnectedMobile = true
            }
            return haveConnectedWifi || haveConnectedMobile
        }


        fun getCurrentTimeStamp(): Long {
            return System.currentTimeMillis()
        }

        fun saveCurrentTime( context: Context, hour: Long) {
            context.getSharedPreferences(Constants.CINEMAS, 0).edit()
                .putLong(Constants.CINEMAS_CURRENT_TIME, hour).apply()
        }

        fun getHours(expires_in: String): Int {
            val hours: Long = TimeUnit.MILLISECONDS.toHours(expires_in.toLong())
            return hours.toInt()
        }

        fun getImageToDisplay(
            current: Movies, routes: ArrayList<Routes>?,
            imageView: ImageView,
            imageType:String
        ) {
            try {
                for (media in current.media!!) {
                    if (!media.code.isNullOrEmpty() &&
                        media.code.lowercase().contentEquals(imageType)
                    ) {
                        for (route in routes!!) {
                            if (route.code.lowercase().contentEquals(media.code)) {
                                if (!route.sizes!!.large.isNullOrEmpty() &&
                                    !media.resource.isNullOrEmpty()
                                ) {
                                    Picasso.get().load(route.sizes!!.large + media.resource)
                                        .into(imageView)
                                }
                            }
                        }

                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }




}