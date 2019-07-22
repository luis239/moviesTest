package com.example.luis.test.extension

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.luis.test.R
import com.example.luis.test.common.LoadingDialog

fun ViewGroup.inflater() = LayoutInflater.from(context)

fun ViewGroup.inflate(layoutRes: Int): View {
    return inflater().inflate(layoutRes, this, false)
}

fun FragmentActivity.showMessage(message: String?,
                                 acceptListener: DialogInterface.OnClickListener? = null,
                                 dismissListener: DialogInterface.OnDismissListener? = null) {
    if (message != null) {
        AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(R.string.yes_action, acceptListener)
            .setOnDismissListener(dismissListener)
            .create()
            .show()
    }
}
fun FragmentActivity.showConfirmation(message: String?,
                                      acceptListener: DialogInterface.OnClickListener? = null) {
    if (message != null) {
        AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(R.string.yes_action, acceptListener)
            .setNegativeButton(R.string.no_action, null)
            .create()
            .show()
    }
}


fun FragmentActivity.showLoadingDialog(title: String? = null, message: String? = null) {
    LoadingDialog.showLoadingDialog(supportFragmentManager, title, message)
}

fun FragmentActivity.hideLoadingDialog() {
    LoadingDialog.hideLoadingDialog(supportFragmentManager)
}