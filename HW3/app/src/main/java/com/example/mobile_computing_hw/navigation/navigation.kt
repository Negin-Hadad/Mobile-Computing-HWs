package com.example.mobile_computing_hw.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import kotlinx.serialization.Serializable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mobile_computing_hw.data.AppDatabase
import com.example.mobile_computing_hw.ui.conversation.ConversationScreen
import com.example.mobile_computing_hw.ui.settings.SettingsScreen

@Serializable
object Conversation

@Serializable
object Settings


@Composable
fun MyApp(database: AppDatabase) {
    val navController = rememberNavController()
    val profileDao = database.profileDao()
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Conversation,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Conversation> {
                ConversationScreen(navController , profileDao)
            }
            composable<Settings> {
                SettingsScreen(navController , profileDao)
            }
        }
    }

}

