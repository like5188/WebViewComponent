package com.like.webview.component

import android.app.Application
import android.content.Context
import com.like.common.util.Logger
import com.like.component.IModuleApplication
import com.tencent.smtt.sdk.QbSdk

class WebViewApplication : IModuleApplication {
    companion object {
        lateinit var sInstance: Application
    }

    override fun attachBaseContext(base: Context) {
    }

    override fun onCreate(application: Application) {
        sInstance = application
        QbSdk.initX5Environment(application, null)
        Logger.d("WebViewApplication onCreate")
    }

    override fun onTerminate(application: Application) {
    }

}