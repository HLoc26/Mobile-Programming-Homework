package com.example.imageloader

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

// Task 4
class ImageLoaderService : Service() {
    private val CHANNEL_ID = "image_loader_channel"
    private val NOTI_ID = 101
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel() // Create notification channel here

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                showNotification("Image Loader Service is running")
                handler.postDelayed(this, 5 * 60 * 1000)
            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Call startForeground immediately
        startForeground(NOTI_ID, buildInitialNotification())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            if (!notificationManager.areNotificationsEnabled()) {
                stopSelf()
                return START_NOT_STICKY
            }
        }

        // Start the periodic task
        runnable.run()
        return START_STICKY
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Image Loader Background Service",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for Image Loader Service notifications"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }


    private fun buildInitialNotification() : Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Image Loader Service")
            .setContentText("Image Loader Service is running in background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun showNotification(message: String){
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Image Loader Service")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTI_ID + 1,notification)
        android.util.Log.d("ImageLoaderService", "Notification sent: $message")
    }

}