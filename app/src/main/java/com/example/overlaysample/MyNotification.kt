package com.example.overlaysample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object MyNotification {
    private const val CHANNEL_ID = "channel_id_overlay_sample"
    private const val CHANNEL_NAME = "オーバーレイ表示の切り替え"
    private const val CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT
    private const val FIRST_LINE = "オーバーレイ表示中"
    private const val SECOND_LINE = "ここから表示・非表示を切り替えられます。"
    private val ACTIVITY = MainActivity::class.java

    /**
     * Set the info for the views that show in the notification panel.
     */
    fun build(context: Context): Notification {
        // Create a notification channel
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE)
        )

        // The PendingIntent to launch our activity if the user selects this notification
        val pendingIntent = PendingIntent.getActivity(
            context, 0, Intent(context, ACTIVITY), 0
        )

        return Notification.Builder(context, CHANNEL_ID)
            .setAutoCancel(false)  // don't dismiss when touched
            .setContentIntent(pendingIntent)  // The intent to send when the entry is clicked
            .setContentTitle(FIRST_LINE)  // the label of the entry
            .setContentText(SECOND_LINE)  // the contents of the entry
            .setSmallIcon(R.drawable.cat)  // the status icon
            .setTicker(context.getText(R.string.app_name))  // the status text
            .setWhen(System.currentTimeMillis())  // the time stamp
            .build()
    }
}
