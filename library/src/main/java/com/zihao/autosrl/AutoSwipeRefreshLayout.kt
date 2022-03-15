package com.zihao.autosrl

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * ClassName AutoSwipeRefreshLayout
 * Describe TODO<一个可以自动刷新的SwipeRefreshLayout>
 * Author zihao
 * Date 2022/3/15 23:37
 * Version v1.0
 */
class AutoSwipeRefreshLayout : SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    /**
     * 自动刷新-利用反射实现自动刷新功能
     */
    fun autoRefresh() {
        try {
            val circleView: Field = SwipeRefreshLayout::class.java.getDeclaredField("mCircleView")
            //这里是拿到下拉那个进度条动画的控件
            circleView.isAccessible = true
            val progress: View = circleView.get(this) as View
            progress.visibility = VISIBLE
            val setRefreshing: Method = SwipeRefreshLayout::class.java.getDeclaredMethod(
                "setRefreshing",
                Boolean::class.javaPrimitiveType,
                Boolean::class.javaPrimitiveType
            )
            //这里是为了获取刷新函数，这里设置为true，就可以
            setRefreshing.isAccessible = true
            setRefreshing.invoke(this, true, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}