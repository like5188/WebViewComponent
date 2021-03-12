package com.like.webview.component.service

import androidx.fragment.app.Fragment
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
     * 用于简单显示网页，而不进行交互。
     */
    fun startWebViewActivity(url: String)

    /**
     * 需要复杂交互时，请使用 [com.like.webview.component.WebViewFragment]。
     */
    fun getWebViewFragment(url: String): Fragment

}