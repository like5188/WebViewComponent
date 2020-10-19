package com.like.webview.component

import android.graphics.PixelFormat
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.like.common.base.BaseAutoWiredActivity
import com.like.common.base.addFragments
import com.like.common.util.AutoWired
import com.like.common.util.startActivityByApplication
import com.like.webview.component.databinding.WebviewActivityWebviewBinding

/**
 * 对 [WebViewFragment] 的封装
 * 用于简单显示网页，而不进行交互，如果需要交互，请使用 [WebViewFragment]
 */
class WebViewActivity : BaseAutoWiredActivity() {
    @AutoWired
    var url: String? = null

    companion object {
        fun start(url: String?) {
            startActivityByApplication<WebViewActivity>("url" to url)
        }
    }

    private val mBinding by lazy {
        DataBindingUtil.setContentView<WebviewActivityWebviewBinding>(
            this,
            R.layout.webview_activity_webview
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置：Activity在onCreate时需要设置:
        window.setFormat(PixelFormat.TRANSLUCENT)
        mBinding
        url?.let {
            addFragments(R.id.fragment_holder, 0, WebViewFragment(it))
        }
    }

}
