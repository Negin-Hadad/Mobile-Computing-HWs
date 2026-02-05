package com.example.mobile_computing_hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.mobile_computing_hw.data.AppDatabase
import com.example.mobile_computing_hw.ui.theme.MobileComputingHWTheme
import com.example.mobile_computing_hw.navigation.MyApp

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

        setContent {
            MobileComputingHWTheme {
                MyApp(db)
            }
        }
    }
}











