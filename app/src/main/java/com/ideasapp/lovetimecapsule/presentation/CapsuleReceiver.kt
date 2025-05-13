package com.ideasapp.lovetimecapsule.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ideasapp.lovetimecapsule.R

class CapsuleReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent:Intent) {
        val capsuleId = intent.getIntExtra("capsule_id", 0)
        val capsuleText = intent.getStringExtra("capsule_text") ?: ""

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "capsule_channel"

        val channel = NotificationChannel(channelId,"Capsule Notifications",NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            capsuleId,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_capsule)
            .setContentTitle("Time Capsule Opened")
            .setContentText("Your capsule is ready to open: $capsuleText")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        openCapsule(capsuleText)
        notificationManager.notify(capsuleId, notification)
    }
}