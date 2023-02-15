package com.me.myquran.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.TypedValue
import android.view.View
import android.view.Window
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


class CommonUtils {
    companion object {
        fun Context.dpToPx(dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
        }

        fun getHtmlSpanned(htmlString: String): Spanned {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT)
            } else {
                return Html.fromHtml(htmlString)
            }
        }

        fun shareText(context: Context, message: String) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        fun isDarkMode(context: Context): Boolean {
            val currentNightMode = (context.resources.configuration.uiMode
                    and Configuration.UI_MODE_NIGHT_MASK)
            return when (currentNightMode) {
                Configuration.UI_MODE_NIGHT_NO ->             // Night mode is not active, we're using the light theme
                    false
                Configuration.UI_MODE_NIGHT_YES ->             // Night mode is active, we're using dark theme
                    true
                else -> false
            }
        }

        @SuppressLint("ObsoleteSdkInt", "InlinedApi")
        fun setupStatusBar(context: Context, window: Window, @ColorRes color:Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = ContextCompat.getColor(context, color)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }
    }
}