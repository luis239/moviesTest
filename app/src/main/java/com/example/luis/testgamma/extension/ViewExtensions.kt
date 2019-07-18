package com.example.luis.testgamma.extension

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.luis.testgamma.R
import com.example.luis.testgamma.common.LoadingDialog

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
            .setPositiveButton(R.string.accion_aceptar, acceptListener)
            .setOnDismissListener(dismissListener)
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