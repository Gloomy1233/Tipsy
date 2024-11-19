package com.example.composeApp.screens.RegistrationUI

// Step4Screen.kt
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.composeApp.screens.ViewModels.RegistrationViewModel
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun Step4Screen(viewModel: RegistrationViewModel) {
    val imageUri = viewModel.profilePictureUri

    // Image Picker Launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.profilePictureUri = uri?.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp), // Replace 16.ssdp with sdp if ssdp is not defined
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's add a profile picture and you're ready to go!\n(You can change this later)",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 18.sp)
        )

        Box(
            modifier = Modifier
                .size(200.sdp)
                .background(Color.LightGray, CircleShape)
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null && imageUri.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(200.sdp)
                        .clip(CircleShape)
                )
            } else {
                Text(text = "Tap to add a picture")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { /* Implement Camera functionality if desired */ }) {
                Icon(Icons.Default.Camera, contentDescription = "Camera")
            }
            IconButton(onClick = { launcher.launch("image/*") }) {
                Icon(Icons.Default.Upload, contentDescription = "Upload")
            }
        }

    }
}
