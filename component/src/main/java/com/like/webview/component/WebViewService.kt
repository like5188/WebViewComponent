package com.like.webview.component

import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.like.webview.X5Listener
import com.like.webview.component.service.IWebViewService
import com.tencent.smtt.sdk.WebView

@AutoService(IWebViewService::class)
class WebViewService : IWebViewService {
    private var mWebViewFragment: WebViewFragment? = null

    override fun getWebViewFragment(url: String): Fragment {
        return WebViewFragment(url).apply {
            mWebViewFragment = this
        }
    }

    override fun startWebViewActivity(url: String) {
        WebViewActivity.start(url)
    }

    override fun getWebView(): WebView? {
        return mWebViewFragment?.getWebView()
    }

    override fun setListener(listener: X5Listener) {
        mWebViewFragment?.setListener(listener)
    }

    override fun setInterfaceName(interfaceName: String) {
        mWebViewFragment?.setInterfaceName(interfaceName)
    }

    override fun registerAndroidMethodForJSCall(methodName: String, method: (String) -> String) {
        mWebViewFragment?.registerAndroidMethodForJSCall(methodName, method)
    }

    override fun callJSMethod(
        methodName: String,
        paramsJsonString: String?,
        callback: ((String) -> Unit)?
    ) {
        mWebViewFragment?.callJSMethod(methodName, paramsJsonString, callback)
    }

    override fun pageUp() {
        mWebViewFragment?.pageUp()
    }

    override fun pageDown() {
        mWebViewFragment?.pageDown()
    }

    override fun reload() {
        mWebViewFragment?.reload()
    }

}
