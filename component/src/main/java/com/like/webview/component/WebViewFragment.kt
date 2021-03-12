package com.like.webview.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.like.common.base.BaseLazyFragment
import com.like.webview.X5Listener
import com.like.webview.X5ProgressBarWebView
import com.like.webview.component.databinding.WebviewFragmentWebviewBinding
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

/**
 * 包含了进度条的 WebView 的封装
 *
 * 注意：
 * 1、WebViewFragment 中的方法必须在 initViews() 方法执行完毕之后调用才有效。
 * 因为 FragmentTransaction 的 commit() 方法是异步的，所以我们不知道什么时候 WebViewFragment 会被创建并添加到 Activity 中。
 * 所以，如果 Activity 在 onCreate() 方法中添加了 WebViewFragment，那么就需要在 onStart()或者onResume()方法中调用相关方法才有效，具体情况有所不同。
 */
class WebViewFragment(private val url: String?) : BaseLazyFragment() {
    private var mX5ProgressBarWebView: X5ProgressBarWebView? = null
    private var mWebView: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<WebviewFragmentWebviewBinding>(
            inflater,
            R.layout.webview_fragment_webview,
            container,
            false
        ) ?: throw RuntimeException("初始化 WebViewFragment 失败")
        mX5ProgressBarWebView = binding.webView
        mWebView = mX5ProgressBarWebView?.getWebView()
        mWebView?.settings?.cacheMode = WebSettings.LOAD_NO_CACHE// 支持微信H5支付
        return binding.root
    }

    override fun onLazyLoadData() {
        super.onLazyLoadData()
        mWebView?.loadUrl(url)
    }

    internal fun getWebView(): WebView? {
        return mWebView
    }

    internal fun setListener(listener: X5Listener) {
        mX5ProgressBarWebView?.setListener(listener)
    }

    internal fun addJavascriptInterface(javascriptInterface: Any, interfaceName: String) {
        mWebView?.addJavascriptInterface(javascriptInterface, interfaceName)
    }

    internal fun pageUp() {
        mWebView?.pageUp(true)
    }

    internal fun pageDown() {
        mWebView?.pageDown(true)
    }

    internal fun reload() {
        mWebView?.reload()
    }

    internal fun callJsMethod(methodName: String, paramsJsonString: String?, callback: ((String) -> Unit)?) {
        mX5ProgressBarWebView?.callJsMethod(methodName, paramsJsonString, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView?.destroy()
    }

}
