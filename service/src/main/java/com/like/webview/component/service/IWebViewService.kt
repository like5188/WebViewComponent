package com.like.webview.component.service

import androidx.fragment.app.Fragment
import com.like.webview.X5Listener
import com.tencent.smtt.sdk.WebView
import java.util.*

/**
 * WebView页面服务接口
 */
interface IWebViewService {
    companion object {
        fun getInstance(): IWebViewService? = Holder.instance
    }

    private object Holder {
        val iterator = ServiceLoader.load(IWebViewService::class.java).iterator()
        val instance = if (iterator.hasNext()) {
            iterator.next()
        } else {
            null
        }
    }

    /**
     * 1、用于简单显示网页，而不进行交互，也就是说不能使用其它方法。
     * 2、如果需要交互，请使用 [getWebViewFragment] 获取 Fragment 进行封装，然后就可以使用其它方法。
     * 注意：其它方法是指：[getWebView]、[setListener]、[addJavascriptInterface]、[pageUp]、[pageDown]、[reload]
     */
    fun startWebViewActivity(url: String)

    fun getWebViewFragment(url: String): Fragment

    fun getWebView(): WebView?

    fun setListener(listener: X5Listener)

    fun addJavascriptInterface(javascriptInterface: Any, interfaceName: String)

    fun pageUp()

    fun pageDown()

    fun reload()

    /**
     * android 调用 js 方法
     *
     * @param methodName        js 方法的名字
     * @param paramsJsonString  js 方法的参数
     * @param callback          回调方法，用于处理 js 方法返回的 String 类型的结果。
     */
    fun callJsMethod(
        methodName: String,
        paramsJsonString: String? = null,
        callback: ((String) -> Unit)? = null
    )
}