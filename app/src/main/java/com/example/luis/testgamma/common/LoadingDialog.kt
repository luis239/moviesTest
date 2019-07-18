package com.example.luis.testgamma.common

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class LoadingDialog : DialogFragment() {

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_MESSAGE = "arg_message"
        const val TAG = "LoadingDialog"


        private fun newInstance(title: String? = null, message: String? = null): LoadingDialog {
            val dialog = LoadingDialog()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_MESSAGE, message)
            dialog.arguments = args
            return dialog
        }

        fun showLoadingDialog(fm: FragmentManager?, title: String? = null, message: String? = null) {
            val fragment = fm?.findFragmentByTag(TAG)
            if (fm != null) {
                val dialog = newInstance(title, message)
                dialog.show(fm, TAG)
            }
        }

        fun hideLoadingDialog(fm: FragmentManager?) {
            (fm?.findFragmentByTag(TAG) as? LoadingDialog)?.dismiss()
        }

    }
}