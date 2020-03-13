package com.like.webview.component

import android.app.Application
import android.content.Context
import com.like.common.base.IModuleApplication
import com.like.common.util.Logger
import com.tencent.smtt.sdk.QbSdk

class WebViewApplication : IModuleApplication {
    override fun attachBaseContext(base: Context?) {
    }

    override fun onCreate(application: Application) {
        QbSdk.initX5Environment(application, null)
        Logger.d("WebViewApplication onCreate")
    }

    override fun onTerminate(application: Application) {
    }

}