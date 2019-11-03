package peixe_urbano.com.hot_deals.util.message

import IAlertConfirm
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import peixe_urbano.com.hot_deals.R


object  MessageUtil {

    fun alert(context: Context, title: String, message: String, cancelable: Boolean): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(context.getString(R.string.ok), null)
            .setCancelable(cancelable)
            .create()
    }

    fun alert(
        context: Context,
        title: String,
        message: String,
        positive: String,
        positiveListener: DialogInterface.OnClickListener,
        negative: String,
        negativeListener: DialogInterface.OnClickListener
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positive, positiveListener)
            .setNegativeButton(negative, negativeListener)
            .create()
    }
}


    fun alertConfirm(
        context: Context,
        title: String,
        message: String,
        cancelable: Boolean,
        target: IAlertConfirm
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(cancelable)
            .setNeutralButton(context.getString(R.string.ok)
            ) { dialog, id -> target.positive(dialog, id) }
            .create()
    }

    fun alertYesNo(
        context: Context,
        title: String,
        message: String,
        cancelable: Boolean,
        target: IAlertConfirm
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(cancelable)
            .setPositiveButton(context.getString(R.string.yes)
            ) { dialog, id -> target.positive(dialog, id) }
            .setNegativeButton(context.getString(R.string.no)
            ) { dialog, id -> target.negative(dialog, id) }
            .create()
    }
