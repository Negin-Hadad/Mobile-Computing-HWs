package com.example.mobile_computing_hw

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.room.Room
import com.example.mobile_computing_hw.data.AppDatabase
import com.example.mobile_computing_hw.ui.theme.MobileComputingHWTheme
import com.example.mobile_computing_hw.navigation.MyApp
import com.example.mobile_computing_hw.utils.GyroscopeManager
import com.example.mobile_computing_hw.utils.NotificationHelper.sendNotification

class MainActivity : ComponentActivity() {

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-db"
        )
            .allowMainThreadQueries()
            .build()


        createNotificationChannel()


        val gyroManager = GyroscopeManager(applicationContext);
        gyroManager.start()

        sendNotification(applicationContext , "Test" , "automatic notification.")

        setContent {
            MobileComputingHWTheme {
                MyApp(db)
            }
        }
    }


    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(getString(R.string.channel_id), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}









