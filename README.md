#### 最新版本

模块|WebView
---|---
最新版本|[![Download](https://jitpack.io/v/like5188/WebView.svg)](https://jitpack.io/#like5188/WebView)

## 功能介绍

1、对 'com.github.like5188:WebView:版本号' 库进行组件化封装的库。

## 使用方法：

1、引用

在Project的gradle中加入：
```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
在Module的gradle中加入：
```groovy
    dependencies {
        implementation 'com.github.like5188:Common:5.7.8'
        implementation 'com.github.like5188:WebView:5.0.5'

        implementation 'com.github.like5188:WebViewComponentService:0.0.4'
        implementation 'com.github.like5188:WebViewComponent:版本号'
    }
```

2、通过 implementation 'com.github.like5188:WebViewComponentService:0.0.4' 库中的 IWebViewService 接口进行相关操作，详情见例子。
