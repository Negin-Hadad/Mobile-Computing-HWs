package com.example.mobile_computing_hw.ui.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_computing_hw.R
import com.example.mobile_computing_hw.data.Profile
import com.example.mobile_computing_hw.data.ProfileDao
import kotlinx.coroutines.flow.debounce
import java.io.File
import coil.compose.AsyncImage

@Composable
fun SettingsScreen(
    navController: NavController,
    profileDao: ProfileDao
) {



    var username by rememberSaveable { mutableStateOf("") }
    var imagePath by rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val profile = profileDao.getProfile(1)
        username = profile?.username ?: "Anonymous"
        imagePath = profile?.profileImage
    }

    LaunchedEffect(Unit) {
        snapshotFlow { username }
            .debounce(500)
            .collect { newUsername ->
                profileDao.insertProfile(
                    Profile(uid = 1, username = newUsername, profileImage = imagePath)
                )
            }
    }

    val currentContext = LocalContext.current

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
//            Log.d("ImagePicker", "Selected URI: $uri")
            val inputStream = currentContext.contentResolver.openInputStream(uri)
            val file = File(currentContext.filesDir, "profile.jpg")
            inputStream?.use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            imagePath = file.absolutePath

            profileDao.insertProfile(
                Profile(uid = 1, username = username, profileImage = imagePath)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(all = 10.dp).clickable {navController.popBackStack()}
                )

        }

        Spacer(modifier = Modifier.height(1.dp))

        Column(
            modifier = Modifier
                .fillMaxSize().padding(all = 10.dp ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = if (imagePath.isNullOrEmpty()) null else imagePath,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clickable {
                        imagePicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    // Create a fake NavController for preview
    val navController = rememberNavController()

    val fakeProfileDao = object : ProfileDao {
        override fun getProfile(id: Int): Profile? {
            TODO("Not yet implemented")
        }

        override fun insertProfile(profile: Profile) {
            TODO("Not yet implemented")
        }
    }

    SettingsScreen(
        navController = navController,
        profileDao = fakeProfileDao,
    )
}

