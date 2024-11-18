package com.example.composeApp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Step2Screenwqe(navController: NavController) {
    MaterialTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Some important info to complete your registration", fontSize = 18.ssp)

        TextField(value = "", onValueChange = {}, label = { Text("Full Name*") }, modifier = Modifier.fillMaxWidth())
        TextField(value = "", onValueChange = {}, label = { Text("E-mail*") }, modifier = Modifier.fillMaxWidth())
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Password*") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(value = "", onValueChange = {}, label = { Text("Repeat Password*") }, modifier = Modifier.fillMaxWidth())
        TextField(value = "", onValueChange = {}, label = { Text("Phone*") }, modifier = Modifier.fillMaxWidth())

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Do you want the number to be visible\n(you can change that later)")
            Switch(checked = false, onCheckedChange = {})
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
            Button(onClick = { navController.navigate("step3") }) {
                Text("Next")
            }
        }
        }
    }
}