package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.R.attr.bottom
import android.graphics.Rect
import android.util.DisplayMetrics



fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

fun Activity.isKeyboardOpen(): Boolean {
    /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128

    val rect = Rect()
    val rootView = this.getRootView()
    rootView.getWindowVisibleDisplayFrame(rect)
    val dm = rootView.resources.displayMetrics
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    val heightDiff = rootView.bottom - rect.bottom
    /* Threshold size: dp to pixels, multiply with display density */
    val isKeyboardOpen = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density
    return isKeyboardOpen
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}