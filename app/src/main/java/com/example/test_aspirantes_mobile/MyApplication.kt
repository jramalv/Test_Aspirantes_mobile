package com.example.test_aspirantes_mobile

import android.app.Application
import android.content.Context

class MyApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        context = getApplicationContext()
    }

    companion object {
        private var context: Context? = null
        val appContext: Context?
            get() = context
    }
}