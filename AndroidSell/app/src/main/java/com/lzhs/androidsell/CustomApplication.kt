package com.lzhs.androidsell

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.lzhs.androidsell.BuildConfig
import com.lzhs.lzhs_library.Utils

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/27 : 4:35 PM<br/>
 */
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        isDebug()
        init()
    }

    private fun isDebug() {
        if (BuildConfig.DEBUG) return
        ARouter.openDebug()
        ARouter.openLog()
    }

    private fun init() {
        Utils.init(this)
        Utils.initDefaultLogUtils(BuildConfig.DEBUG)
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }
}