package com.tragicfruit.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.tragicfruit.weatherapp.R
import com.tragicfruit.weatherapp.utils.ViewHelper
import com.tragicfruit.weatherapp.utils.getViewId

class SettingsListItemView : RelativeLayout {

    private val title = TextView(context)
    private val subtitle = TextView(context)

    @JvmOverloads
    constructor(context: Context, attributeSet: AttributeSet? = null, style: Int = 0): super(context, attributeSet, style) {
        title.setTextAppearance(context, R.style.TextAppearance_AppCompat_Body2)
        addView(title, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(ViewHelper.px(R.dimen.app_margin_xx).toInt(),
                ViewHelper.px(R.dimen.app_margin_xx).toInt(), ViewHelper.px(R.dimen.app_margin_xx).toInt(), 0)
        })

        subtitle.setTextAppearance(context, R.style.TextAppearance_AppCompat_Body1)
        addView(subtitle, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            addRule(BELOW, title.getViewId())
            setMargins(ViewHelper.px(R.dimen.app_margin_xx).toInt(), 0,
                ViewHelper.px(R.dimen.app_margin_xx).toInt(), ViewHelper.px(R.dimen.app_margin_xx).toInt())
        })

        attributeSet?.let {
            val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SettingsListItemView)
            title.text = typedArray.getString(R.styleable.SettingsListItemView_title)
            typedArray.recycle()
        }
    }

    fun setTitle(@StringRes resId: Int) {
        title.setText(resId)
    }

    fun setSubtitle(@StringRes resId: Int) {
        subtitle.setText(resId)
    }

    fun setSubtitle(subtitleText: String) {
        subtitle.text = subtitleText
    }

}