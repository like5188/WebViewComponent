#### 最新版本

模块|WebViewComponent
---|---
最新版本|[![Download](https://jitpack.io/v/like5188/WebViewComponent.svg)](https://jitpack.io/#like5188/WebViewComponent)

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
        implementation 'com.github.like5188:Component:1.0.2'
        implementation 'com.github.like5188:Common:6.5.2'
        implementation 'com.github.like5188.WebViewComponent:service:版本号'
        implementation 'com.github.like5188.WebViewComponent:component:版本号'
    }
```

2、通过 implementation 'com.github.like5188.WebViewComponent:service:版本号' 库中的 IWebViewService 接口进行相关操作，详情见例子。
