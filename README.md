#### 最新版本

模块|WebView
---|---
最新版本|[![Download](https://jitpack.io/v/like5188/WebView.svg)](https://jitpack.io/#like5188/WebView)

## 功能介绍

1、对 implementation 'com.github.like5188:WebView:版本号' 库，用 ARouter 进行的组件化封装。

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
        implementation 'com.github.like5188:Common:5.3.3'
        implementation 'com.github.like5188:WebView:5.0.5'
        implementation 'com.github.like5188:WebViewComponentService:0.0.1'
        implementation 'com.github.like5188:WebViewComponent:版本号'
        kapt 'com.alibaba:arouter-compiler:1.2.2'
    }
```

2、通过 implementation 'com.github.like5188:WebViewComponentService:0.0.1' 库中的 WebViewService 接口进行相关操作，详情见例子。
