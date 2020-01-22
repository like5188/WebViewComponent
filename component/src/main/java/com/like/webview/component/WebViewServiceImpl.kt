package com.like.webview.component

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.like.webview.X5Listener
import com.like.webview.component.service.WebViewService
import com.tencent.smtt.sdk.WebView

@Route(path = Consts.SERVICE_WEB_VIEW, name = "WebView页面路由服务实现")
class WebViewServiceImpl : WebViewService {
    private var mContext: Context? = null
    private var mWebViewFragment: WebViewFragment? = null

    override fun init(context: Context) {
        mContext = context
    }

    override fun getWebViewFragment(url: String): Fragment {
        val fragment = WebViewFragment.get(url)
        mWebViewFragment = fragment
        return fragment
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

    override fun callJSMethod(methodName: String, paramsJsonString: String?, callback: ((String) -> Unit)?) {
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
