package com.example.hilttutorialbydanny.utils

import android.app.Activity
import android.content.Intent
import android.view.View

//StartNewActivity
//ProgressBar
//Button isEnabled
//HanbleApiError
//Snakbar
//Logout if available
//

//Start Activity
fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

//Progress Bar
fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

//Button enabled
fun View.enableButton(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (isEnabled) 1f else 0.5f
}




