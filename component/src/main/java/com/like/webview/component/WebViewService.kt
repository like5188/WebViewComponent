package com.like.webview.component

import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.like.webview.component.service.IWebViewService

@AutoService(IWebViewService::class)
class WebViewService : IWebViewService {

    override fun startWebViewActivity(url: String) {
        WebViewActivity.start(url)
    }

    override fun getWebViewFragment(url: String): Fragment {
        return WebViewFragment(url)
    }

}
