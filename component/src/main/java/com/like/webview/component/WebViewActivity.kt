package com.like.webview.component

import android.graphics.PixelFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.like.webview.component.databinding.WebviewActivityWebviewBinding
import com.like.webview.component.service.WebViewService

/**
 * 对 [WebViewFragment] 的封装
 * 用于简单显示网页，而不进行交互，如果需要交互，请使用 [WebViewFragment]
 */
@Route(path = Consts.UI_WEB_VIEW_ACTIVITY)
class WebViewActivity : AppCompatActivity() {
    companion object {
        fun start(url: String) {
            ARouter.getInstance().build(Consts.UI_WEB_VIEW_ACTIVITY)
                .withString("url", url)
                .navigation()
        }
    }

    private val mBinding by lazy {
        DataBindingUtil.setContentView<WebviewActivityWebviewBinding>(this, R.layout.webview_activity_webview)
    }
    @Autowired
    @JvmField
    var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置：Activity在onCreate时需要设置:
        window.setFormat(PixelFormat.TRANSLUCENT)
        ARouter.getInstance().inject(this)
        mBinding
        url?.let {
            val fragment = WebViewFragment.get(it)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_holder, fragment)
                // 触发BaseFragment的lazyLoadData()方法
                hide(fragment)
                show(fragment)
            }.commit()
        }
    }

}
