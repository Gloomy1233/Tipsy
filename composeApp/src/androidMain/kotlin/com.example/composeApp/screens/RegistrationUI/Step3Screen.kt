package com.example.composeApp.screens.RegistrationUI

// Step3Screen.kt
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step3Screen(viewModel: RegistrationViewModel) {
    // State for Sex dropdown
    var selectedSex by remember { mutableStateOf(viewModel.sex) }
    var sexExpanded by remember { mutableStateOf(false) }
    val sexOptions = listOf("Male", "Female", "Other")

    // State for Relationship Status dropdown
    var selectedStatus by remember { mutableStateOf(viewModel.relationshipStatus) }
    var statusExpanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Single", "In a relationship", "Married", "It's complicated")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp), // Replace 16.sdp with dp if sdp is not defined
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "You can leave this blank and edit it later",
            style = TextStyle(fontSize = 18.ssp)
        )

        // Date of Birth TextField (you might want to use a date picker here)
        OutlinedTextField(
            value = viewModel.dateOfBirth,
            onValueChange = { viewModel.dateOfBirth = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth()
        )

        // Sex Dropdown
        ExposedDropdownMenuBox(
            expanded = sexExpanded,
            onExpandedChange = { sexExpanded = !sexExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedSex,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sex") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = sexExpanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = sexExpanded,
                onDismissRequest = { sexExpanded = false }
            ) {
                sexOptions.forEach { sex ->
                    DropdownMenuItem(
                        text = { Text(text = sex) },
                        onClick = {
                            selectedSex = sex
                            sexExpanded = false
                            viewModel.sex = sex
                        }
                    )
                }
            }
        }

        // Relationship Status Dropdown
        ExposedDropdownMenuBox(
            expanded = statusExpanded,
            onExpandedChange = { statusExpanded = !statusExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedStatus,
                onValueChange = {},
                readOnly = true,
                label = { Text("Relationship Status") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = statusExpanded,
                onDismissRequest = { statusExpanded = false }
            ) {
                statusOptions.forEach { status ->
                    DropdownMenuItem(
                        text = { Text(text = status) },
                        onClick = {
                            selectedStatus = status
                            statusExpanded = false
                            viewModel.relationshipStatus = status
                        }
                    )
                }
            }
        }

        // Bio TextField

        OutlinedTextField(
            value = viewModel.bio,
            onValueChange = { viewModel.bio = it },
            label = { Text("Bio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.sdp)
        )


    }
}
