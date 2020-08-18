package com.soerjdev.dicodingbfaasubmission.data

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.soerjdev.dicodingbfaasubmission.view.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, "onReceive()")

        val title = intent?.getStringExtra("title")
        val message = intent?.getStringExtra("message")

        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)



        context?.let {
            AlarmUtil.showNotification(
                context = context,
                title = title ?: "Alarm Title",
                message = message ?: "Alarm Message",
                notificationId = 0,
                pendingIntent = pendingIntent
            )
        }
    }

    companion object {
        var TAG = AlarmReceiver::class.java.simpleName
    }

}