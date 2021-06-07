package com.erkuai.common

import android.app.Application
import android.util.Log
import com.erkuai.common.config.BaseConfig

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.i(BaseConfig.TAG, "common/BaseApplication onCreate: ")
    }
}