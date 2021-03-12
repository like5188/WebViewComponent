package com.like.webview.component

import android.util.Log
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

    override fun addJavascriptInterface(javascriptInterface: Any, interfaceName: String) {
        mWebViewFragment?.addJavascriptInterface(javascriptInterface, interfaceName)
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

    /**
     * android 调用 js 方法
     *
     * @param methodName        js 方法的名字
     * @param paramsJsonString  js 方法的参数
     * @param callback          回调方法，用于处理 js 方法返回的 String 类型的结果。
     */
    override fun callJsMethod(
        methodName: String,
        paramsJsonString: String?,
        callback: ((String) -> Unit)?
    ) {
        val webView = getWebView() ?: return
        if (methodName.isEmpty()) return
        val jsString = if (paramsJsonString.isNullOrEmpty()) {
            "javascript:$methodName()"
        } else {
            "javascript:$methodName('$paramsJsonString')"
        }
//        webView.post { webView.loadUrl(jsString) }// Ui线程
        // a)比第一种方法效率更高、使用更简洁，因为该方法的执行不会使页面刷新，而第一种方法（loadUrl ）的执行则会。
        // b)Android 4.4 后才可使用
        webView.evaluateJavascript(jsString) {
            Log.v(
                "JavascriptInterface",
                "android调用js方法，方法名：$methodName，传递的参数：paramsJsonString=$paramsJsonString，js方法的返回值：$it"
            )
            callback?.invoke(it)
        }
    }
}
