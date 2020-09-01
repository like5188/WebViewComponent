package com.like.webview.component.app

import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.like.common.base.BaseActivity
import com.like.common.util.Logger
import com.like.webview.X5Listener
import com.like.webview.component.app.databinding.ActivityTestBinding
import com.like.webview.component.service.IWebViewService
import com.tencent.smtt.sdk.WebView
import org.json.JSONObject

class TestActivity : BaseActivity() {
    private val mBinding by lazy {
        DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置：Activity在onCreate时需要设置:
        window.setFormat(PixelFormat.TRANSLUCENT)
        mBinding
        val url = "file:///android_asset/index.html"
//        val url = "https://www.sina.com.cn/"
        IWebViewService.getInstance()?.getWebViewFragment(url)?.let {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_holder, it)
                // 触发BaseFragment的lazyLoadData()方法
                hide(it)
                show(it)
            }.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        initWebViewFragment()
    }

    private fun initWebViewFragment() {
        IWebViewService.getInstance()?.setInterfaceName("androidAPI")
        IWebViewService.getInstance()?.registerAndroidMethodForJSCall("androidMethodName") {
            try {
                val jsonObject = JSONObject(it)
                val name = jsonObject.optString("name")
                val age = jsonObject.optInt("age")
                Logger.d("androidMethodName name=$name age=$age")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            "js调用android方法成功"
        }
        IWebViewService.getInstance()?.setListener(object : X5Listener {
            override fun onReceivedIcon(webView: WebView?, icon: Bitmap?) {
                mBinding.ivIcon.setImageBitmap(icon)
            }

            override fun onReceivedTitle(webView: WebView?, title: String?) {
                if (title != null && title.length > 6)
                    mBinding.tvTitle.text = "${title.subSequence(0, 6)}..."
                else
                    mBinding.tvTitle.text = title
            }

            override fun onProgressChanged(webView: WebView?, progress: Int?) {
            }

            override fun onPageStarted(webView: WebView?, url: String?, favicon: Bitmap?) {
            }

            override fun onPageFinished(webView: WebView?, url: String?) {
            }

            override fun onReceivedError(webView: WebView?) {
            }
        })
    }

    fun startWebViewActivity(view: View) {
        IWebViewService.getInstance()?.startWebViewActivity(this, "https://www.sina.com.cn/")
    }

    fun pageUp(view: View) {
        IWebViewService.getInstance()?.pageUp()
    }

    fun pageDown(view: View) {
        IWebViewService.getInstance()?.pageDown()
    }

    fun reload(view: View) {
        IWebViewService.getInstance()?.reload()
    }

    fun callJSMethod(view: View) {
        try {
            val params = JSONObject()
            params.put("name", "like1")
            params.put("age", 22)
            IWebViewService.getInstance()?.callJSMethod("jsMethodName", params.toString()) {
                Logger.d("callJsMethod 返回值：$it")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
