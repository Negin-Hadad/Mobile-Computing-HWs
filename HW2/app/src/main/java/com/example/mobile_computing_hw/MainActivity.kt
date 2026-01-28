package com.example.mobile_computing_hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mobile_computing_hw.ui.theme.MobileComputingHWTheme
import com.example.mobile_computing_hw.navigation.MyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileComputingHWTheme {
                MyApp()
            }
        }
    }
}











