package id.ac.ubaya.a160419107_ubayakost.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.view.MainActivity

class NotificationHelper(val context:Context) {
    private val CHANNEL_ID = "todo_channel_id"
    private val NOTIFICATION_ID =1

    private fun notificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_ID,
            NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Todo channel description"
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title:String, message:String){
        notificationChannel()
        val intent =Intent(context, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        }

        val pendingIntent = PendingIntent.getActivity(context,0,intent,0)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.iconkost)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)
    }
}