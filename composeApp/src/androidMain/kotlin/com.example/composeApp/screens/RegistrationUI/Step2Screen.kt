package com.example.composeApp.screens.RegistrationUI
// Step2Screen.kt
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import com.example.composeApp.screens.ViewModels.RegistrationViewModel
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun Step2Screen(viewModel: RegistrationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp), // Replace 16.sdp with dp if sdp is not defined
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Some important info to complete your registration",
            style = TextStyle(fontSize = 16.ssp) // Adjust font size as needed
        )

        OutlinedTextField(
            value = viewModel.fullName,
            onValueChange = { viewModel.fullName = it },
            label = { Text("Full Name*") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("E-mail*") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password*") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.repeatPassword,
            onValueChange = { viewModel.repeatPassword = it },
            label = { Text("Repeat Password*") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.phone,
            onValueChange = { viewModel.phone = it },
            label = { Text("Phone*") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Do you want the number to be visible?\n(You can change this later)",
                style = TextStyle(fontSize = 12.ssp)
            )
            Switch(
                checked = viewModel.isPhoneVisible,
                onCheckedChange = { viewModel.isPhoneVisible = it }
            )
        }


    }
}
