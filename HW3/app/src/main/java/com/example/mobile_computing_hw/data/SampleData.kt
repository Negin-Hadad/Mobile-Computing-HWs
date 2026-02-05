package com.example.mobile_computing_hw.data

import com.example.mobile_computing_hw.ui.conversation.Message

object SampleData {
    // Sample conversation data

    fun conversationSample(username: String, profileImage: String?): List<Message> {

        val conversationSample = listOf(
            Message(
                username,
                "Test...Test...Test...",
                profileImage,
            ),
            Message(
                username,
                """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim(),
                profileImage
            ),
            Message(
                username,
                """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim(),
                profileImage
            ),
            Message(
                username,
                "Searching for alternatives to XML layouts...",
                profileImage
            ),
            Message(
                username,
                """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim(),
                profileImage
            ),
            Message(
                username,
                "It's available from API 21+ :)",
                profileImage
            ),
            Message(
                username,
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?",
                profileImage
            ),
            Message(
                username,
                "Android Studio next version's name is Arctic Fox",
                profileImage
            ),
            Message(
                username,
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^",
                profileImage
            ),
            Message(
                username,
                "I didn't know you can now run the emulator directly from Android Studio",
                profileImage
            ),
            Message(
                username,
                "Compose Previews are great to check quickly how a composable layout looks like",
                profileImage
            ),
            Message(
                username,
                "Previews are also interactive after enabling the experimental setting",
                profileImage
            ),
            Message(
                username,
                "Have you tried writing build.gradle with KTS?",
                profileImage
            ),
            Message(
                username,
                "Have you tried writing build.gradle with KTS?",
                profileImage
            ),
            Message(
                username,
                "Have you tried writing build.gradle with KTS?",
                profileImage
            ),
            Message(
                username,
                "Have you tried writing build.gradle with KTS?",
                profileImage
            ),
        )

        return  conversationSample
    }

}