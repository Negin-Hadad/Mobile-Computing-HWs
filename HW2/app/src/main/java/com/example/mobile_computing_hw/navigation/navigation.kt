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
import com.example.mobile_computing_hw.ui.conversation.ConversationScreen
import com.example.mobile_computing_hw.ui.settings.SettingsScreen

@Serializable
object Conversation

@Serializable
data class Settings(val name: String)


@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Conversation,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Conversation> {
                ConversationScreen(navController)
            }
            composable<Settings> { backStackEntry ->
                val setting: Settings = backStackEntry.toRoute()
                SettingsScreen(navController , setting.name)
            }
        }
    }

}

