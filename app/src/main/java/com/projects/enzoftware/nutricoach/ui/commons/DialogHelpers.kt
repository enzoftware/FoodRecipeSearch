package com.projects.enzoftware.nutricoach.ui.commons

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.projects.enzoftware.nutricoach.R

/**
 *
 * Show an Alert Error Dialog
 *
 * @param context Context of the current activity
 */
fun showGeneralError(context: Context) {
    AlertDialog.Builder(context)
        .setTitle(context.resources.getString(R.string.error_title))
        .setMessage(context.resources.getString(R.string.error_message))
        .setPositiveButton(context.resources.getString(R.string.accept_button_text))
        { dialog, _ -> dialog.dismiss() }
        .show()
}


//fun showDeleteAdvertice(context: Context, onConfirm: () -> Unit) {
//    AlertDialog.Builder(context)
//        .setTitle(context.resources.getString(R.string.warning_title))
//        .setMessage(context.resources.getString(R.string.warning_message))
//        .setPositiveButton(context.resources.getString(R.string.yes_txt))
//        { dialog, _ -> onConfirm() }
//        .setNegativeButton(context.resources.getString(R.string.no_txt))
//        { dialog, _ -> dialog.dismiss() }
//        .show()
//}

/**
 *
 * Util method to show an LENGTH_SHORT toast
 *
 * @param context Context of the current activity
 * @param text Text that would be shown in the Toast
 */
fun shortToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

/**
 *
 * Util method to show an LENGTH_LONG toast
 *
 * @param context Context of the current activity
 * @param text Text that would be shown in the Toast
 */
fun longToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}